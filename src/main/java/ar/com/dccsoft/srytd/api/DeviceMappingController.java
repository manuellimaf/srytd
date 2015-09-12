package ar.com.dccsoft.srytd.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.model.DeviceMapping;
import ar.com.dccsoft.srytd.services.DeviceMappingService;

@Path("/device-mapping")
public class DeviceMappingController {

	private static Logger logger = LoggerFactory.getLogger(DeviceMappingController.class);
	private DeviceMappingService service = new DeviceMappingService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DeviceMapping> getAllDevices() {
		logger.debug("Loading all device mappings");
		return service.getAllDeviceMappings();
	}

}
