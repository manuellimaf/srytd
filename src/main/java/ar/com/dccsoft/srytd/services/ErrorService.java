package ar.com.dccsoft.srytd.services;

import org.apache.commons.lang3.exception.ExceptionUtils;

import ar.com.dccsoft.srytd.daos.ProcessErrorDao;

public class ErrorService {

	private ProcessErrorDao errorDao = new ProcessErrorDao();
	private AlertService alertService = new AlertService();
	
	public void handleError(Long errorId, String message, String username, Throwable t) {
		errorDao.saveError(errorId, message, username, ExceptionUtils.getStackTrace(t));
		alertService.sendAlert(errorId, username);
	}

	
}
