package ar.com.dccsoft.srytd.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.model.Role;
import ar.com.dccsoft.srytd.services.UserService;

@Path("/auth")
public class LoginController {

	private UserService userService = new UserService();
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@POST
	public Response authenticate() {
		logger.info("Already authenticated");
		return Response.ok().build();
	}

	@GET
	@Path("/role")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRole(@Context HttpServletRequest httpRequest) {
		String user = httpRequest.getRemoteUser();
		logger.info("Getting role for user: " + user);
		Role role = userService.findByName(user).getRole();
		return role.name();
	}

	@POST
	@Path("/invalidate")
	public Response logout(@Context HttpServletRequest httpRequest) {
		logger.info("Logout");
		httpRequest.getSession().invalidate();
		return Response.ok().build();
	}

}
