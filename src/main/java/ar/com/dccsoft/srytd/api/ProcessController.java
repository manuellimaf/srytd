package ar.com.dccsoft.srytd.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.ProcessService;
import ar.com.dccsoft.srytd.utils.ui.Paginable;

@Path("/process")
public class ProcessController {

	private static Logger logger = LoggerFactory.getLogger(ProcessController.class);
	private ProcessService service = new ProcessService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Paginable getAllProcesses() {
		logger.debug("Loading all processes");
		return new Paginable(service.getAll());
	}

}
