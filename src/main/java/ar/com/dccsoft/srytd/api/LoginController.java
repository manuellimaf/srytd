package ar.com.dccsoft.srytd.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/auth")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@POST
	public Response authenticate() {
		logger.info("Already authenticated");
		return Response.ok().build();
	}

	@POST
	@Path("/invalidate")
	public Response logout(@Context HttpServletRequest httpRequest) {
		logger.info("Logout");
		httpRequest.getSession().invalidate();
		return Response.ok().build();
	}

}
