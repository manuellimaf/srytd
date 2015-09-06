package ar.com.dccsoft.srytd.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.UserService;

@Path("/auth")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	private UserService service = new UserService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticate() {
		logger.info("Already authenticated");
		return Response.ok().build();
	}

}
