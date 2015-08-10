package ar.com.dccsoft.srytd.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.ProcessDao;

public class ProcessService {

	private static Logger logger = LoggerFactory.getLogger(ProcessService.class);
	private ProcessDao processDao = new ProcessDao();
	private ErrorService errorService = new ErrorService();
	
	public Long create(Date from, String username) {
		try {
		Long processId = processDao.create(from, username).getId();
		return processId;
		} catch (Throwable t) {
			Long errorId = System.currentTimeMillis();
			String message = String.format("[%d] Error creating process for date %tc", errorId, from);
			logger.error(message, t);
			errorService.handleError(errorId, message, username, t);
			throw t;
		}
	}

}
