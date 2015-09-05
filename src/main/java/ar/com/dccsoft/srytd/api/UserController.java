package ar.com.dccsoft.srytd.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jersey.repackaged.com.google.common.collect.Lists;
import ar.com.dccsoft.srytd.api.dto.Page;

@Path("/user")
public class UserController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page getAll() {
		// TODO
		return new Page(Lists.newArrayList(), 0L);
	}

	public boolean authenticate() {
		// TODO
		return true;
	}

}
