package ar.com.dccsoft.srytd.services;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ar.com.dccsoft.srytd.daos.ProcessErrorDao;

public class ErrorService {

	private static Logger logger = LoggerFactory.getLogger(ErrorService.class);
	private ProcessErrorDao errorDao = new ProcessErrorDao();
	private AlertService alertService = new AlertService();

	public void handleError(Long errorId, String message, Throwable t) {
		String username = currentUser();
		errorDao.saveError(errorId, message, username, ExceptionUtils.getStackTrace(t));
		alertService.sendAlert(errorId, username);
	}

	private String currentUser() {
		try {
			return MDC.get("username");
		} catch (Exception e) {
			logger.error("Username not found in context.");
		}
		return "unknown";
	}

}
