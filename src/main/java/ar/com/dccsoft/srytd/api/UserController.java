package ar.com.dccsoft.srytd.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jersey.repackaged.com.google.common.collect.Lists;
import ar.com.dccsoft.srytd.utils.ui.Paginable;

@Path("/user")
public class UserController {

	public boolean authenticate() {
		return true;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Paginable getAll() {
		return new Paginable(Lists.newArrayList(), 0L);
	}
}
