package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static java.lang.String.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.model.Device;
import ar.com.dccsoft.srytd.model.FieldValue;
import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessStatus;
import ar.com.dccsoft.srytd.services.FileBuilder.FileBuildResult;
import ar.com.dccsoft.srytd.utils.MDCUtils;
import ar.com.dccsoft.srytd.utils.MDCUtils.MDCKey;
import ar.com.dccsoft.srytd.utils.ftp.FTPConnector;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Processor {

	private static Logger logger = LoggerFactory.getLogger(Processor.class);

	private FieldValueService fieldValueService = new FieldValueService();
	private MappedFieldValueService mappedFieldValueService = new MappedFieldValueService();
	private DeviceService deviceService = new DeviceService();
	private ProcessService processService = new ProcessService();
	private FTPConnector ftpConnector = new FTPConnector();
	private AppPropertyService propService = new AppPropertyService();
	private NotificationsService notificationsService = new NotificationsService();
	private ProcessAlertService processAlertService = new ProcessAlertService();

	public void start(Date from, String username) {
		MDCUtils.put(MDCKey.USER, username);
		long startTime = System.currentTimeMillis();

		tryAndInform("Unhandled error", () -> {
			logger.info(format("Starting process for date %s", formatDate(from)));

			// Iniciar el proceso
			Process process = processService.create(from, username);

			runFullProcess(process);
			return null;
		});  

		long duration = (System.currentTimeMillis() - startTime);
		logger.info(format("Process finished after %d millis", duration));
		MDCUtils.clear();
	}

	public void runFullProcess(Process process) {
		logger.info("Process started");
		Long processId = process.getId();
		MDCUtils.put(MDCKey.PROCESS_ID, processId.toString());
		
		List<MappedFieldValue> mappings = mapFieldValues(process);

		buildFileAndSend(process, mappings);
	}

	public List<MappedFieldValue> mapFieldValues(Process process) {
		// Leer datos de campo
		List<FieldValue> fieldValues = fieldValueService.readOneHourValues(process.getValuesFrom());

		// Leer mapeos de tags
		List<Device> devices = deviceService.getAllDevices();

		// Realizar validaciones
		performValidations(fieldValues, devices);

		// Persistir valores mapeados
		List<MappedFieldValue> mappings = mappedFieldValueService.mapAndSave(process, fieldValues, devices);

		// Leer valores manuales y unirlos a la lista de valores automáticos
		mappings.addAll(mappedFieldValueService.safetlyGetValuesForProcess(process));
		return mappings;
	}

	public void buildFileAndSend(Process process, List<MappedFieldValue> mappings) {
		Long processId = process.getId();
		MDCUtils.put(MDCKey.PROCESS_ID, processId.toString());
		
		// Generar txt (csv)
		FileBuilder fileBuilder = new FileBuilder().withMappings(mappings);
		FileBuildResult result = fileBuilder.build();
		processService.updateStatus(processId, ProcessStatus.PROCESSED);
		verifyResult(result);

		// Persistir txt
		processService.saveFile(processId, result.getFile());

		// Subir a FTPServer
		String fileName = fileName(process.getValuesFrom());
		ftpConnector.transfer(result.getFile(), fileName);
		processService.updateStatus(processId, ProcessStatus.SENT);

		// Enviar mail de resultado
		sendNotification(formatDate(process.getValuesFrom()), result.getFile(), fileName);

		// Actualizar el estado final
		processService.updateFinalStatus(processId);
	}

	private void verifyResult(FileBuildResult result) {
		if (result.getUnprocessedValues() > 0) {
			long total = result.getUnprocessedValues() + result.getProcessedValues();
			// Marcar el proceso para finalización con warning
			processAlertService.addWarning("Some readings could not be processed", 
					format("%d readings out of %d could not be processed.", result.getUnprocessedValues(), total));

		}

	}

	private void sendNotification(String dateStr, InputStream is, String fileName) {
		try {
			notificationsService.sendFinishMessage(dateStr, IOUtils.toByteArray(is), fileName);
		} catch (IOException e) {
			logger.error("Error trying to send after process notifications", e);
		}
	}

	private String fileName(Date from) {
		String companyId = propService.getCompanyId();
		String facilityId = propService.getFacilityId();
		String suffix = "res318_mediciones.txt";

		return String.format("%s_%s_%tY%tm%td%tH%tM_%s", companyId, facilityId, from, from, from, from, from, suffix);
	}

	private String formatDate(Date from) {
		return format("%tY-%tm-%td %tH:%tM", from, from, from, from, from);
	}

	private void performValidations(List<FieldValue> fieldValues, List<Device> mappings) {
		Set<String> tagNames = Sets.newHashSet(Lists.transform(mappings, mapping -> mapping.getName()));
		Set<String> fieldTagNames = Sets.newHashSet(Lists.transform(fieldValues, fieldVale -> fieldVale.getDeviceId()));

		if (!tagNames.containsAll(fieldTagNames)) {
			fieldTagNames.removeAll(tagNames);
			String notFound = Arrays.toString(fieldTagNames.toArray(new String[0]));
			logger.info(format("Tag Mappings not found for: %s", notFound));
			
			// Marcar el proceso para finalización con warning
			processAlertService.addWarning("Unmapped devices", format("This devices does not have mappings: %s", notFound));
		}
	}
}
