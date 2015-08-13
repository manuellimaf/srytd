package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.ProcessDao;

public class ProcessService {

	private static Logger logger = LoggerFactory.getLogger(ProcessService.class);
	private ProcessDao processDao = new ProcessDao();

	public Long create(Date from, String username) {
		String errorMessage = String.format("Error creating process for date %tc", from);
		return tryAndInform(errorMessage, () -> {
			return processDao.create(from, username).getId();
		});
	}

	public void saveFile(Long processId, byte[] byteArray) {
		try {
			String file = new String(byteArray);
			processDao.saveFile(processId, file);
		} catch (Throwable t) {
			logger.error("Error persisting generated file to database", t);
		}

	}
}
