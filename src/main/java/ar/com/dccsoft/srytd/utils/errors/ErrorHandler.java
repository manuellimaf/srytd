package ar.com.dccsoft.srytd.utils.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.ProcessAlertService;

public class ErrorHandler {

	private static Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
	private static ProcessAlertService errorService = new ProcessAlertService();

	public static <A> A tryAndInform(String errorMessage, ErrorProneTask<A> task) {
		try {
			A result = task.call();
			return result;
		} catch (ProcessException e) {
			// Do not save errors twice
			throw e;
		} catch (Throwable t) {
			Long errorId = System.currentTimeMillis();
			String message = String.format("[%d] %s", errorId, errorMessage);
			logger.error(message, t);
			errorService.handleError(errorId, errorMessage, t);
			throw new ProcessException(errorId, message, t);
		} 
	}

}
