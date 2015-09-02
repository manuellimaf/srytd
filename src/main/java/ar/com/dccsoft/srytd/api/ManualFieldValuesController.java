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

import ar.com.dccsoft.srytd.api.dto.ManualValuesDTO;
import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.services.ManualFieldValueService;

@Path("/manual-field-values")
public class ManualFieldValuesController {

	private static Logger logger = LoggerFactory.getLogger(ManualFieldValuesController.class);
	private ManualFieldValueService service = new ManualFieldValueService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page getManualValues(@QueryParam("start") String start, @QueryParam("limit") String limit) {
		logger.debug("Loading manual values");
		return service.getPage(Integer.valueOf(start), Integer.valueOf(limit));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveValue(ManualValuesDTO dto) {
		logger.info("Persisting manual value");

		// TODO - username
		String username = "web";
		// TODO - validateCreation(dto);
		// TODO - service.createMapping(dto, username);
		
		return Response.status(200).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateManualValue(ManualValuesDTO dto) {
		logger.info("Updating manual value id " + dto.getId());

		// TODO - validateUpdate(dto);
		// TODO - service.updateMapping(dto);
		
		return Response.status(200).build();
	}



	@DELETE
	@Path("/{id}")
	public Response deleteManualValue(@PathParam("id") String id) {
		logger.info("Deleting manual value id " + id);

		// TODO - validateId(id);
		// TODO - validar que no se borren datos ya enviados
		
		// TODO 
//		service.deleteMapping(Long.valueOf(id));
		
		return Response.status(200).build();
	}
	

}
