package ar.com.dccsoft.srytd.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.model.ProcessResult;
import ar.com.dccsoft.srytd.services.MappedFieldValueService;
import ar.com.dccsoft.srytd.services.ProcessService;
import ar.com.dccsoft.srytd.utils.ui.Paginable;

@Path("/process")
public class ProcessController {

	private static Logger logger = LoggerFactory.getLogger(ProcessController.class);
	private ProcessService service = new ProcessService();
	private MappedFieldValueService mfvService = new MappedFieldValueService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Paginable getAllProcesses() {
		logger.debug("Loading all processes");
		return new Paginable(service.getAll());
	}

	@GET
	@Path("/mapped-field-values")
	@Produces(MediaType.APPLICATION_JSON)
	public Paginable getMappedValues(@QueryParam("processId") String processId) {
		logger.info(String.format("Loading all values for process %s", processId));

		// TODO . validate
		List<MappedFieldValue> values = mfvService.getValuesForProcess(Long.valueOf(processId));
		return new Paginable(values);
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

}
