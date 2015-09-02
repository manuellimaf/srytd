package ar.com.dccsoft.srytd.utils.errors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class Validator {

	public static void validateOrFail(String errorMessage, CallableTask<Boolean> task) {
		if (!task.call()) {
			throw new UserException(errorMessage);
		}
	}

	public static void validateOrNotFound(CallableTask<Boolean> task) {
		if (!task.call()) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}
}
