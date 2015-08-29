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

import ar.com.dccsoft.srytd.daos.ProcessAlertDao;
import ar.com.dccsoft.srytd.daos.ProcessDao;
import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessAlert;
import ar.com.dccsoft.srytd.model.ProcessResult;
import ar.com.dccsoft.srytd.model.ProcessStatus;
import ar.com.dccsoft.srytd.utils.errors.ProcessException;

public class ProcessService {

	private static Logger logger = LoggerFactory.getLogger(ProcessService.class);
	private ProcessAlertDao alertDao = new ProcessAlertDao();
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
			process.getResult().setFile(data);
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

	public void updateFinalStatus(Process process, ProcessException e) {
		transactional(MySQL, (session) -> {
			ProcessAlert error = alertDao.find(e.getErrorId());
			ProcessResult result = process.getResult();
			result.setError(error);
//			processDao.update(process);
			return null;
		});
	}

	public void updateFinalStatus(Process process) {
		ProcessResult result = process.getResult();
		result.setStatus(result.getWarnings().isEmpty() ? ProcessStatus.FINISHED_OK.toString() : ProcessStatus.FINISHED_WARN.toString());
		updateStatus(process, ProcessStatus.FINISHED_OK);
	}

	public List<Process> getAll() {
		return transactional(MySQL, (session) -> processDao.getAll());
	}

	public Process getProcess(Long id) {
		return transactional(MySQL, (session) -> processDao.find(id));
	}
}
