package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.api.dto.StartProcessDTO;
import ar.com.dccsoft.srytd.daos.ProcessDao;
import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessResult;
import ar.com.dccsoft.srytd.model.ProcessStatus;
import ar.com.dccsoft.srytd.services.process.Processor;

public class ProcessService {

	private static Logger logger = LoggerFactory.getLogger(ProcessService.class);
	private MappedFieldValueService mappingsService = new MappedFieldValueService();
	private ProcessDao processDao = new ProcessDao();

	public Process create(Date from, String username) {
		String errorMessage = String.format("Error creating process for date %tc", from);
		return tryAndInform(errorMessage, () -> {
			return transactional(MySQL, (session) -> {
				return processDao.create(from, username);
			});
		});
	}

	public void saveFile(Long processId, String data, String fileName) {
		try {
			logger.info("Persisting file to database");
			transactional(MySQL, (session) -> {
				Process process = processDao.find(processId);
				process.getResult().setFile(data);
				process.getResult().setFileName(fileName);
				processDao.update(process);
				return null;
			});
		} catch (Throwable t) {
			logger.error("Error persisting generated file to database", t);
		}
		logger.info("File persisted to database");
	}

	public void updateStatus(Long processId, ProcessStatus status) {
		transactional(MySQL, (session) -> {
			Process process = processDao.find(processId);
			process.setStatus(status);
			processDao.update(process);
			return null;
		});
		logger.info(String.format("Updating process state -> %s", status.toString()));
	}

	public void updateSentStatus(Long processId, Long sent, Long unsent) {
		transactional(MySQL, (session) -> {
			Process p = processDao.find(processId);
			ProcessResult pr = p.getResult();
			pr.setSentValues(sent);
			pr.setUnsentValues(unsent);
			processDao.update(p);

			updateStatus(processId, ProcessStatus.SENT);
			return null;
		});
	}

	public void updateFinalStatus(Long processId) {
		transactional(MySQL, (session) -> {
			Process process = processDao.find(processId);
			ProcessResult result = process.getResult();
			result.setStatus(result.getWarnings().isEmpty() ? ProcessStatus.FINISHED_OK : ProcessStatus.FINISHED_WARN);
			processDao.update(process);

			updateStatus(processId, ProcessStatus.FINISHED);
			return null;
		});
	}

	public Page getPage(Integer start, Integer limit) {
		return transactional(MySQL, (session) -> {
			List<Process> elems = processDao.getPage(start, limit);
			Long total = processDao.countAll();
			return new Page(elems, total);
		});
	}

	public Process getProcess(Long id) {
		return transactional(MySQL, (session) -> processDao.find(id));
	}

	public Long resendValues(Long id, String username) {
		Process process = getProcess(id);
		logger.info("Cloning process with id " + id);
		
		Process newProcess = create(process.getValuesFrom(), username);
		List<MappedFieldValue> newMappings = mappingsService.cloneMappings(process, newProcess);
		
		new Processor().buildFileAndSend(newProcess, newMappings);

		return newProcess.getId();
	}

	public void startProcess(StartProcessDTO dto, String username) {
		Date dateFrom = parseDateFrom(dto);
		Processor processor = new Processor();
		processor.start(dateFrom, username);
	}

	public Date parseDateFrom(StartProcessDTO dto) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(2);

		String dateStr = String.format("%s %s:00", dto.getDateFrom(), nf.format(dto.getHourFrom()));
		Date dateFrom;
		try {
			dateFrom = DateUtils.parseDate(dateStr, "dd/MM/yyyy HH:mm");
		} catch (ParseException e) {
			// It's soposed to be validated in a previous step
			throw new RuntimeException(e);
		}
		return dateFrom;
	}
	
}
