package ar.com.dccsoft.srytd.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.model.Device;
import ar.com.dccsoft.srytd.services.DeviceService;

@Path("/device")
public class DeviceController {

	private static Logger logger = LoggerFactory.getLogger(DeviceController.class);
	private DeviceService service = new DeviceService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Device> getAllDevices() {
		logger.debug("Loading all devices");
		return service.getAllDevices();
	}

}
