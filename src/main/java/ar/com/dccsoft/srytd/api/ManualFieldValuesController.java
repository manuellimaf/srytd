package ar.com.dccsoft.srytd.api;

import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrFail;
import static ar.com.dccsoft.srytd.utils.errors.Validator.validateOrNotFound;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNumeric;

import java.text.ParseException;
import java.util.Date;

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

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.ManualValuesDTO;
import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.model.ProcessStatus;
import ar.com.dccsoft.srytd.services.ManualFieldValueService;

@Path("/manual-field-values")
public class ManualFieldValuesController {

	private static Logger logger = LoggerFactory.getLogger(ManualFieldValuesController.class);
	private ManualFieldValueService service = new ManualFieldValueService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page getManualValues(@QueryParam("start") String start, @QueryParam("limit") String limit) {
		logger.debug("Loading manual values");
		return service.getPage(Integer.valueOf(start), Integer.valueOf(limit));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveValue(ManualValuesDTO dto, @Context HttpServletRequest request) {
		logger.info("Persisting manual value");

		String username = request.getRemoteUser();
		createValidations(dto);
		service.createManualValue(dto, username);
		
		return Response.status(200).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateManualValue(ManualValuesDTO dto) {
		logger.info("Updating manual value id " + dto.getId());

		updateValidations(dto);
		service.updateManualValue(dto);
		
		return Response.status(200).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteManualValue(@PathParam("id") String id) {
		logger.info("Deleting manual value id " + id);

		deleteValidations(id);
		service.deleteManualValue(Long.valueOf(id));
		
		return Response.status(200).build();
	}
	
	private void updateValidations(ManualValuesDTO dto) {
		validateOrNotFound(() -> {
			if(dto.getId() != null) {
				 MappedFieldValue value = service.find(dto.getId());
				 if(value != null) {
					 validateOrFail("No se puede actualizar un registro enviado", () -> 
					 value.getProcess() == null || 
					 ProcessStatus.ERROR.equals(value.getProcess().getResult().getStatus()));
					 
					 return true;
				 }
			}
			return false;	
		});
		genericValidations(dto);
	}

	private void createValidations(ManualValuesDTO dto) {
		genericValidations(dto);
	}

	private void deleteValidations(String id) {
		validateOrFail(id + " no es un id válido", () -> isNumeric(id));
		
		MappedFieldValue value = service.find(Long.valueOf(id));
		validateOrNotFound(() -> value != null);

		validateOrFail("No se puede eliminar un registro enviado", () -> 
			value.getProcess() == null || 
			ProcessStatus.ERROR.equals(value.getProcess().getResult().getStatus()));

	}
	

	public void genericValidations(ManualValuesDTO dto) {
		validateOrFail("Dispositivo es requerido", () -> isNotEmpty(dto.getDeviceId()));
		validateOrFail("Tag es requerido", () -> isNotEmpty(dto.getTag()));
		validateOrFail("Fecha y hora de medición son requeridos", () -> 
			isNotEmpty(dto.getValueDate()) && isNotEmpty(dto.getValueDate()));
		validateOrFail("Debe ingresar la hora de inicio de la transacción", () -> 
			isNotEmpty(dto.getItDate()) ? isNotEmpty(dto.getItTime()) : true);
		validateOrFail("Debe ingresar la hora de fin de la transacción", () -> 
			isNotEmpty(dto.getFtDate()) ? isNotEmpty(dto.getFtTime()) : true);
		
		validateOrFail("Formato inválido de fecha y hora de medición (o fechas en el futuro)", () -> 
			isValidDate(dto.getValueDate() + " " + dto.getValueTime(), "dd/MM/yyyy HH:mm"));
		validateOrFail("Formato inválido de fecha y hora de inicio de transacción (o fechas en el futuro)", () -> 
			isNotEmpty(dto.getItDate()) ? isValidDate(dto.getItDate() + " " + dto.getItTime(), "dd/MM/yyyy HH:mm") : true);
		validateOrFail("Formato inválido de fecha y hora de fin de transacción (o fechas en el futuro)", () -> 
			isNotEmpty(dto.getFtDate()) ? isValidDate(dto.getFtDate() + " " + dto.getFtTime(), "dd/MM/yyyy HH:mm") : true);
	}

	private boolean isValidDate(String dateStr, String... formats) {
		Date date;
		try {
			date = DateUtils.parseDate(dateStr, formats);
		} catch (ParseException e) {
			return false;
		}
		return date != null && date.before(new Date());
	}
	

}
