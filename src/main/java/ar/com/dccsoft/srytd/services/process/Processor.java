package ar.com.dccsoft.srytd.services.process;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static java.lang.String.format;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jersey.repackaged.com.google.common.collect.Maps;

import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.model.DeviceMapping;
import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.TagValue;
import ar.com.dccsoft.srytd.services.AppPropertyService;
import ar.com.dccsoft.srytd.services.DeviceMappingService;
import ar.com.dccsoft.srytd.services.ManualFieldValueService;
import ar.com.dccsoft.srytd.services.MappedFieldValueService;
import ar.com.dccsoft.srytd.services.NotificationsService;
import ar.com.dccsoft.srytd.services.ProcessAlertService;
import ar.com.dccsoft.srytd.services.ProcessService;
import ar.com.dccsoft.srytd.services.TagValueService;
import ar.com.dccsoft.srytd.services.process.FileBuilder.FileBuildResult;
import ar.com.dccsoft.srytd.utils.MDCUtils;
import ar.com.dccsoft.srytd.utils.MDCUtils.MDCKey;
import ar.com.dccsoft.srytd.utils.ftp.FTPConnector;
import ar.com.dccsoft.srytd.utils.ftp.FTPConnectorType;

import com.google.common.collect.Lists;

public class Processor {

	private static Logger logger = LoggerFactory.getLogger(Processor.class);

	private TagValueService fieldValueService = new TagValueService();
	private MappedFieldValueService mappedFieldValueService = new MappedFieldValueService();
	private ManualFieldValueService manualFieldValueService = new ManualFieldValueService();
	private DeviceMappingService deviceMappingService = new DeviceMappingService();
	private ProcessService processService = new ProcessService();
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
		
		List<MappedFieldValue> mappings = getMappedFieldValues(process, process.getStartedBy());

		buildFileAndSend(process, mappings);
	}

	@SuppressWarnings("unchecked")
	private List<MappedFieldValue> getMappedFieldValues(Process process, String username) {
		// Leer mapeos de dispositivos con sus tags
		List<DeviceMapping> devices = deviceMappingService.getAllDeviceMappings();
		
		// Leer datos de campo
		Set<TagValue> tagValues = fieldValueService.readOneHourValues(process.getValuesFrom(), devices);

		// Mapear valores de campo
		List<MappedFieldValue> fieldValues = mappedFieldValueService.map(tagValues, devices);
		
		// Persistir valores de campo mapeados
		mappedFieldValueService.save(fieldValues, process, username);
		
		// Leer valores manuales
		List<MappedFieldValue> manualValues = manualFieldValueService.readOneHourValues(process.getValuesFrom());
		
		// Asocio los valores manuales a un proceso
		manualFieldValueService.update(manualValues, process);
		
		// Unir ambas listas de valores y perservar solo los que tengan fecha más cercana al final de la hora
		List<MappedFieldValue> unprocessed = removeDuplicates(ListUtils.sum(fieldValues, manualValues));

		return unprocessed;
	}

	public void buildFileAndSend(Process process, List<MappedFieldValue> mappings) {
		Long processId = process.getId();
		MDCUtils.put(MDCKey.PROCESS_ID, processId.toString());
		
		// Generar txt (csv)
		FileBuilder fileBuilder = new FileBuilder().withMappings(mappings);
		FileBuildResult result = fileBuilder.build();
		verifyResult(result);

		// Persistir txt
		String fileName = fileName(process.getValuesFrom());
		processService.saveFile(processId, result.getFile(), fileName);

		// Subir a FTPServer
		FTPConnector ftpConnector = FTPConnectorType.valueOf(propService.getFTPConfig().getType()).getInstance();
		ftpConnector.transfer(result.getFile(), fileName);
		processService.updateSentStatus(processId, result.getProcessedValues(), result.getUnprocessedValues());

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

	private void sendNotification(String dateStr, String data, String fileName) {
		try {
			notificationsService.sendFinishMessage(dateStr, data.getBytes(), fileName);
		} catch (Exception e) {
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

	private List<MappedFieldValue> removeDuplicates(List<MappedFieldValue> values) {
		Map<String, MappedFieldValue> valuesByDevice = Maps.newHashMap();
		for(MappedFieldValue fieldValue : values) {
			String device = fieldValue.getDeviceId();
			MappedFieldValue currMax = valuesByDevice.get(device);
			if(currMax != null) {
				currMax = currMax.getTimestamp().after(fieldValue.getTimestamp()) ? currMax : fieldValue;
			} else {
				currMax = fieldValue;
			}
			valuesByDevice.put(device, currMax);
		}
		
		return Lists.newLinkedList(valuesByDevice.values());
	}


}
