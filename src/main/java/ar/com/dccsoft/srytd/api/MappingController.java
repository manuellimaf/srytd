package ar.com.dccsoft.srytd.api;

import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrFail;
import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrNotFound;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

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

import org.apache.commons.lang3.StringUtils;
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

		// TODO - username
		String username = "web";
		validateCreation(dto);
		service.createMapping(dto, username);
		
		return Response.status(200).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMapping(MappingDTO dto) {
		logger.info("Updating mapping id " + dto.getId());

		validateUpdate(dto);
		service.updateMapping(dto);
		
		return Response.status(200).build();
	}


	@DELETE
	@Path("/{id}")
	public Response deleteMapping(@PathParam("id") String id) {
		logger.info("Deleting mapping id " + id);

		validateId(id);
		service.deleteMapping(Long.valueOf(id));
		
		return Response.status(200).build();
	}
	
	private void validateCreation(MappingDTO dto) {
		requiredValidations(dto);
		validateOrFail("El Tag ya se encuentra asociado a otro dispositivo", () -> {
			return service.existsMappingForDevice(dto.getName());
		});
		validateOrFail("El dispositivo ya se encuentra mapeado", () -> {
			return service.existsMappingForTag(dto.getName());
		});
		
	}

	private void validateId(String id) {
		validateOrFail(id + " no es un id válido", () -> StringUtils.isNumeric(id));		
		validateOrNotFound(() -> service.existsDevice(Long.valueOf(id)));
	}
	
	private void validateUpdate(MappingDTO dto) {
		requiredValidations(dto);
		validateOrFail("El dispositivo ya posee un mapeo", () -> {
			return service.isValidMappingForDevice(dto.getTag(), dto.getName());
		});
		validateOrFail("Ya existe un dispositivo con este tag", () -> {
			return service.isValidMappingForTag(dto.getTag(), dto.getName());
		});
		
	}

	public void requiredValidations(MappingDTO dto) {
		validateOrFail("Dispositivo es requerido", () -> isNotEmpty(dto.getName()));
		validateOrFail("Tag es requerido", () -> isNotEmpty(dto.getTag()));
	}

}