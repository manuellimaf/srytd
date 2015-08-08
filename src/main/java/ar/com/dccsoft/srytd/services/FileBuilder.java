package ar.com.dccsoft.srytd.services;

import static java.lang.String.format;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ar.com.dccsoft.srytd.daos.FieldValueDao;
import ar.com.dccsoft.srytd.daos.ProcessDao;
import ar.com.dccsoft.srytd.model.FieldValue;

public class FileBuilder {
	
	private static Logger logger = LoggerFactory.getLogger(FileBuilder.class);
	
	private ProcessDao processDao = new ProcessDao();
	private FieldValueDao fieldValueDao = new FieldValueDao();
			
	public void start(Date from, String username) {
		MDC.put("user", username);
		long startTime = System.currentTimeMillis();
		logger.info(format("Starting process for date %tc", from));
		
		// Iniciar el proceso
		Long processId = processDao.create(from, username).getId();
		MDC.put("processId", processId.toString());
		logger.info("Process started");

		// Leer datos de campo
		Date to = DateUtils.addHours(from, 1);
		logger.info(format("Reading field values for: %tc-%tc", from, to));
		List<FieldValue> fieldValues = fieldValueDao.readFieldValues(from, to);
		logger.info(format("%d field values read", fieldValues.size()));
		
		// TODO . Leer mapeos de tags
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
