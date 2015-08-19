package ar.com.dccsoft.srytd.services;

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
import org.slf4j.MDC;

import ar.com.dccsoft.srytd.model.FieldValue;
import ar.com.dccsoft.srytd.model.TagMapping;
import ar.com.dccsoft.srytd.utils.ftp.FTPConnector;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Processor {

	private static Logger logger = LoggerFactory.getLogger(Processor.class);

	private FieldValueService fieldValueService = new FieldValueService();
	private TagMappingService tagService = new TagMappingService();
	private ProcessService processService = new ProcessService();
	private FTPConnector ftpConnector = new FTPConnector();
	private AppPropertyService propService = new AppPropertyService();
	private NotificationsService notificationsService = new NotificationsService();

	public void start(Date from, String username) {
		MDC.put("user", username);
		long startTime = System.currentTimeMillis();
		String dateStr = format("%tY-%tm-%td %tH:%tM", from, from, from, from, from);
		logger.info(format("Starting process for date %s", dateStr));

		// Iniciar el proceso
		Long processId = processService.create(from, username);
		MDC.put("processId", processId.toString());
		logger.info("Process started");

		// Leer datos de campo
		List<FieldValue> fieldValues = fieldValueService.readOneHourValues(from);

		// Leer mapeos de tags
		List<TagMapping> mappings = tagService.getAllMappings();

		// Realizar validaciones
		performValidations(fieldValues, mappings);

		// Generar txt (csv)
		FileBuilder fileBuilder = new FileBuilder().withMappings(mappings).withValues(fieldValues);
		InputStream is = fileBuilder.build();

		// Persistir txt
		processService.saveFile(processId, is);

		// Subir a FTPServer
		String fileName = fileName(from);
		ftpConnector.transfer(is, fileName);

		// Enviar mail de resultado
		sendNotification(dateStr, is, fileName);

		long duration = (System.currentTimeMillis() - startTime);
		logger.info(format("Process finished after %d millis", processId, duration));
		MDC.clear();
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

	private void performValidations(List<FieldValue> fieldValues, List<TagMapping> mappings) {
		Set<String> tagNames = Sets.newHashSet(Lists.transform(mappings, mapping -> mapping.getName()));
		Set<String> fieldTagNames = Sets.newHashSet(Lists.transform(fieldValues, fv -> fv.getTag()));

		if (!tagNames.containsAll(fieldTagNames)) {
			fieldTagNames.removeAll(tagNames);
			String notFound = Arrays.toString(fieldTagNames.toArray(new String[0]));
			logger.info(format("Tag Mappings not found for: %s", notFound));
			// TODO . Marcar el proceso para finalización con warning
		}
	}
}
