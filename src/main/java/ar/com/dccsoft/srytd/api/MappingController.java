package ar.com.dccsoft.srytd.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.DeviceService;
import ar.com.dccsoft.srytd.utils.ui.Page;

@Path("/mapping")
public class MappingController {

	private static Logger logger = LoggerFactory.getLogger(MappingController.class);
	private DeviceService service = new DeviceService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page getAllMappings(@QueryParam("start") String start, @QueryParam("limit") String limit) {
		logger.debug("Loading mappings");
		return service.getPage(Integer.valueOf(start), Integer.valueOf(limit));
	}


}
