package ar.com.dccsoft.srytd.services;

import static java.lang.String.format;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ar.com.dccsoft.srytd.daos.FieldValueDao;
import ar.com.dccsoft.srytd.daos.TagMappingDao;
import ar.com.dccsoft.srytd.model.FieldValue;
import ar.com.dccsoft.srytd.model.TagMapping;

public class FileBuilder {

	private static Logger logger = LoggerFactory.getLogger(FileBuilder.class);

	private FieldValueDao fieldValueDao = new FieldValueDao();
	private TagMappingDao tagMappingDao = new TagMappingDao();
	private ProcessService processService = new ProcessService();
	
	public void start(Date from, String username) {
		MDC.put("user", username);
		long startTime = System.currentTimeMillis();
		logger.info(format("Starting process for date %tc", from));

		// Iniciar el proceso
		Long processId = processService.create(from, username);
		MDC.put("processId", processId.toString());
		logger.info("Process started");

		// Leer datos de campo
		Date to = DateUtils.addHours(from, 1);
		logger.info(format("Reading field values for: %tc-%tc", from, to));
		List<FieldValue> fieldValues = fieldValueDao.readFieldValues(from, to);
		logger.info(format("%d field values read", fieldValues.size()));

		// Leer mapeos de tags
		logger.info("Reading tag mappings");
		List<TagMapping> mappings = tagMappingDao.getAll();
		logger.info(format("%d tag mappings read", mappings.size()));

		// TODO . Realizar validaciones
		// TODO . Generar txt (csv)
		// TODO . Persistir txt
		// TODO . Subir a FTPServer
		// TODO . Enviar mail de resultado

		long duration = (System.currentTimeMillis() - startTime);
		logger.info(format("Process finished after %d millis", processId, duration));
		MDC.clear();
	}

}
