package ar.com.dccsoft.srytd.api;

import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrFail;
import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrNotFound;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.MappingDTO;
import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.services.DeviceMappingService;

@Path("/mapping")
public class MappingController {

	private static Logger logger = LoggerFactory.getLogger(MappingController.class);
	private DeviceMappingService service = new DeviceMappingService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page getAllMappings(@QueryParam("start") String start, @QueryParam("limit") String limit) {
		logger.info("Loading mappings");
		return service.getPage(Integer.valueOf(start), Integer.valueOf(limit));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMapping(MappingDTO dto, @Context HttpServletRequest request) {
		logger.info("Creating new mapping");

		String username = request.getRemoteUser();
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
		validateOrFail("El dispositivo ya se encuentra mapeado", () -> {
			return !service.existsMappingForDevice(dto.getName());
		});
		// TODO - validar que no se repitan los tags
	}

	private void validateId(String id) {
		validateOrFail(id + " no es un id válido", () -> StringUtils.isNumeric(id));		
		validateOrNotFound(() -> service.existsDevice(Long.valueOf(id)));
	}
	
	private void validateUpdate(MappingDTO dto) {
		requiredValidations(dto);
		validateOrNotFound(() -> service.existsDevice(dto.getId()));
		
		validateOrFail("El dispositivo ya posee un mapeo", () -> {
			return service.isValidDevice(dto.getId(), dto.getName());
		});
		
		// TODO - validar que no se repitan los tags
	}

	public void requiredValidations(MappingDTO dto) {
		validateOrFail("Dispositivo es requerido", () -> isNotEmpty(dto.getName()));
		validateOrFail("Código es requerido", () -> isNotEmpty(dto.getCode()));
		validateOrFail("Offset es requerido", () -> dto.getTimeOffset() != null);
	}

}
