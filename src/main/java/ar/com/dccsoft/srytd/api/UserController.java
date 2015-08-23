package ar.com.dccsoft.srytd.api;

import javax.ws.rs.Path;

@Path("/user")
public class UserController {

	public boolean authenticate() {
		return true;
	}
}
