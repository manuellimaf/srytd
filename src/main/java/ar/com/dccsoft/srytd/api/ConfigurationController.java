package ar.com.dccsoft.srytd.api;

import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrFail;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.PropertiesDTO;
import ar.com.dccsoft.srytd.services.AppPropertyService;

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
		logger.info("Updating configuration");

		validateUpdate(dto);
		service.updateProperties(dto);
		return Response.ok().build();
	}

	private void validateUpdate(PropertiesDTO dto) {
		validateOrFail("Código de empresa es requerido", () -> isNotEmpty(dto.getCompanyCode()));
		validateOrFail("Punto de envío es requerido", () -> isNotEmpty(dto.getFacilityCode()));
		validateOrFail("IP es requerido", () -> isNotEmpty(dto.getIp()));
		validateOrFail("Puerto es requerido", () -> dto.getFacilityCode() != null);
		validateOrFail("Usuario (ftp) es requerido", () -> isNotEmpty(dto.getFtpUser()));
		validateOrFail("Password (ftp) es requerido", () -> isNotEmpty(dto.getFtpPassword()));

	}
	
}
