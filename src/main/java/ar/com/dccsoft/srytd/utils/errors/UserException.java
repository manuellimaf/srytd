package ar.com.dccsoft.srytd.utils.errors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserException extends WebApplicationException {

	private static int USER_EXCEPTION_CODE = 450;

	public UserException() {
		super(Response.status(USER_EXCEPTION_CODE).build());
	}

	public UserException(String message) {
		super(Response.status(USER_EXCEPTION_CODE).entity(message).type("text/plain").build());
	}
}
