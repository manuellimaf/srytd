package ar.com.dccsoft.srytd.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessResult;
import ar.com.dccsoft.srytd.services.MappedFieldValueService;
import ar.com.dccsoft.srytd.services.ProcessService;
import ar.com.dccsoft.srytd.utils.ui.Page;

@Path("/process")
public class ProcessController {

	private static Logger logger = LoggerFactory.getLogger(ProcessController.class);
	private ProcessService service = new ProcessService();
	private MappedFieldValueService mfvService = new MappedFieldValueService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page getAllProcesses(@QueryParam("start") String start, @QueryParam("limit") String limit) {
		logger.debug("Loading processes");
		return service.getPage(Integer.valueOf(start), Integer.valueOf(limit));
	}

	@GET
	@Path("/mapped-field-values")
	@Produces(MediaType.APPLICATION_JSON)
	public Page getMappedValues(@QueryParam("processId") String processId, @QueryParam("start") String start, 
			@QueryParam("limit") String limit) {
		
		logger.info(String.format("Loading values for process %s", processId));

		// TODO . validate
		return mfvService.getPage(Long.valueOf(processId), Integer.valueOf(start), Integer.valueOf(limit));
	}

	@GET
	@Path("/result")
	@Produces(MediaType.APPLICATION_JSON)
	public ProcessResult getResult(@QueryParam("processId") String processId) {
		logger.info(String.format("Loading result for process %s", processId));

		// TODO . validate
		ProcessResult result = service.getProcess(Long.valueOf(processId)).getResult();

		return result;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Process getProcess(@PathParam("id") String processId) {
		logger.debug("Loading process id: " + processId);
		// TODO . validate
		return service.getProcess(Long.valueOf(processId));
	}


}
