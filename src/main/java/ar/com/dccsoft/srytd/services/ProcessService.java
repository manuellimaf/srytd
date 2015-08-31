package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.ProcessDao;
import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessResult;
import ar.com.dccsoft.srytd.model.ProcessStatus;
import ar.com.dccsoft.srytd.utils.ui.Page;

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

	public void saveFile(Long processId, InputStream is, String fileName) {
		try {
			logger.info("Persisting file to database");
			String data = IOUtils.toString(is);
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
}
