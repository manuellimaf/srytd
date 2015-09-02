package ar.com.dccsoft.srytd.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.MappingDTO;
import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.services.DeviceService;

@Path("/mapping")
public class MappingController {

	private static Logger logger = LoggerFactory.getLogger(MappingController.class);
	private DeviceService service = new DeviceService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page getAllMappings(@QueryParam("start") String start, @QueryParam("limit") String limit) {
		logger.info("Loading mappings");
		return service.getPage(Integer.valueOf(start), Integer.valueOf(limit));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMapping(MappingDTO dto) {
		logger.info("Creating new mapping");

		// TODO - validate - username
		String username = "web";
		service.createMapping(dto, username);
		
		return Response.status(200).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMapping(MappingDTO dto) {
		logger.info("Updating mapping id " + dto.getId());

		service.updateMapping(dto);
		
		return Response.status(200).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteMapping(@PathParam("id") String id) {
		logger.info("Deleting mapping id " + id);

		// TODO - validate
		service.deleteMapping(Long.valueOf(id));
		
		return Response.status(200).build();
	}
}
