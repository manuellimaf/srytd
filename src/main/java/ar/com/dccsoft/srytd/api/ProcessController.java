package ar.com.dccsoft.srytd.api;

import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrFail;
import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrNotFound;

import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.api.dto.StartProcessDTO;
import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessResult;
import ar.com.dccsoft.srytd.services.MappedFieldValueService;
import ar.com.dccsoft.srytd.services.ProcessService;

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
		
		validateId(processId);
		
		return mfvService.getPage(Long.valueOf(processId), Integer.valueOf(start), Integer.valueOf(limit));
	}

	@GET
	@Path("/result")
	@Produces(MediaType.APPLICATION_JSON)
	public ProcessResult getResult(@QueryParam("processId") String processId) {
		logger.info(String.format("Loading result for process %s", processId));

		validateId(processId);
		ProcessResult result = service.getProcess(Long.valueOf(processId)).getResult();

		return result;
	}

	@POST
	@Path("/{id}/resend")
	@Produces(MediaType.TEXT_PLAIN)
	public Long resendValues(@PathParam("id") String processId, @Context HttpServletRequest request) {
		logger.info(String.format("Starting to send values for process %s", processId));

		String username = request.getRemoteUser();

		validateId(processId);
		Long newId = service.resendValues(Long.valueOf(processId), username);
		return newId;
	}

	@GET
	@Path("/{id}/file")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getFile(@PathParam("id") String processId) {
		logger.debug("Downloading file for process id: " + processId);

		validateId(processId);
		ProcessResult result = service.getProcess(Long.valueOf(processId)).getResult();

		String file = result.getFile();
		String fileName = result.getFileName();
		validateOrNotFound(() -> file != null);
		
		StreamingOutput stream = new StreamingOutput() {
			@Override
			public void write(OutputStream output) throws WebApplicationException {
				try {
					output.write(file.getBytes());
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};

		String contentDispostition = String.format("attachment; filename = %s", fileName);
		return Response.ok(stream).header("content-disposition", contentDispostition).build();
	}

	@POST
	@Path("/start")
	@Consumes(MediaType.APPLICATION_JSON)
	public void startProcess(StartProcessDTO dto, @Context HttpServletRequest request) {
		logger.info("'Start process' received");

		validateStartProcess(dto);
		String username = request.getRemoteUser();
		service.startProcess(dto, username);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Process getProcess(@PathParam("id") String processId) {
		logger.debug("Loading process id: " + processId);

		validateId(processId);
		return service.getProcess(Long.valueOf(processId));
	}

	
	private void validateId(String id) {
		validateOrFail(id + " no es un id válido", () -> StringUtils.isNumeric(id));		
		validateOrNotFound(() -> service.getProcess(Long.valueOf(id)) != null);
	}
	
	private void validateStartProcess(StartProcessDTO dto) {
		validateOrFail("La fecha no puede ser posterior a la actual", () -> {
			Date from = service.parseDateFrom(dto);
			return from.before(new Date());
		});		
		
	}


}
