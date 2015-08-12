package ar.com.dccsoft.srytd.utils.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.ErrorService;
import ar.com.dccsoft.srytd.services.ProcessService;

public class ErrorHandler {

	private static Logger logger = LoggerFactory.getLogger(ProcessService.class);
	private static ErrorService errorService = new ErrorService();

	public static <A> A tryAndInform(String errorMessage, ErrorProneTask<A> task) {
		try {
			A result = task.call();
			return result;
		} catch (Throwable t) {
			Long errorId = System.currentTimeMillis();
			String message = String.format("[%d] %s", errorId, errorMessage);
			logger.error(message, t);
			errorService.handleError(errorId, message, t);
			throw t;
		}
	}

}
