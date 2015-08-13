package ar.com.dccsoft.srytd.services;

import static java.lang.String.format;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ar.com.dccsoft.srytd.model.FieldValue;
import ar.com.dccsoft.srytd.model.TagMapping;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Processor {

	private static Logger logger = LoggerFactory.getLogger(Processor.class);

	private FieldValueService fieldValueService = new FieldValueService();
	private TagMappingService tagService = new TagMappingService();
	private ProcessService processService = new ProcessService();

	public void start(Date from, String username) {
		MDC.put("user", username);
		long startTime = System.currentTimeMillis();
		logger.info(format("Starting process for date %tY-%tm-%td %tH:%tM", from, from, from, from, from));

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
		ByteArrayOutputStream os = fileBuilder.build();

		// Persistir txt
		processService.saveFile(processId, os.toByteArray());

		// TODO . Subir a FTPServer
		// TODO . Enviar mail de resultado

		long duration = (System.currentTimeMillis() - startTime);
		logger.info(format("Process finished after %d millis", processId, duration));
		MDC.clear();
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
