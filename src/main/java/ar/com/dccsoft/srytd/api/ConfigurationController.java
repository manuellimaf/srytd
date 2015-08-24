package ar.com.dccsoft.srytd.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.AppPropertyService;
import ar.com.dccsoft.srytd.services.AppPropertyService.PropertiesDTO;

@Path("/config")
public class ConfigurationController {

	private static Logger logger = LoggerFactory.getLogger(ConfigurationController.class);
	private AppPropertyService service = new AppPropertyService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PropertiesDTO getConfig() {
		logger.info("Loading config");
		return service.getPropertiesDTO();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateConfig(PropertiesDTO dto) {
		logger.info("update!!");

		service.updateProperties(dto);
		return Response.status(200).build();
	}
}
