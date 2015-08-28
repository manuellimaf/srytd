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
import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessStatus;

public class ProcessService {

	private static Logger logger = LoggerFactory.getLogger(ProcessService.class);
	private ProcessDao processDao = new ProcessDao();

	public Process create(Date from, String username) {
		String errorMessage = String.format("Error creating process for date %tc", from);
		return tryAndInform(errorMessage, () -> {
			return transactional(MySQL, (session) -> {
				return processDao.create(from, username);
			});
		});
	}

	public void saveFile(Process process, InputStream is) {
		try {
			logger.info("Persisting file to database");
			String data = IOUtils.toString(is);
			process.setFile(data);
			transactional(MySQL, (session) -> {
				processDao.update(process);
				return null;
			});
		} catch (Throwable t) {
			logger.error("Error persisting generated file to database", t);
		}
		logger.info("File persisted to database");
	}

	public void updateStatus(Process process, ProcessStatus status) {
		process.setStatus(status);
		transactional(MySQL, (session) -> {
			processDao.update(process);
			return null;
		});
		logger.info(String.format("Updating process state -> %s", status.toString()));
	}

	public void updateFinalStatus(Process process, ProcessStatus status, Long sent, Long unsent) {
		process.setSentValues(sent);
		process.setUnsentValues(unsent);
		updateStatus(process, status);
	}

	public List<Process> getAll() {
		return transactional(MySQL, (session) -> processDao.getAll());
	}

	public Process getProcess(Long id) {
		return transactional(MySQL, (session) -> processDao.find(id));
	}
}
