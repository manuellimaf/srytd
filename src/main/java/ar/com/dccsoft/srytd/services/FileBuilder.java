package ar.com.dccsoft.srytd.services;

import static java.lang.String.format;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ar.com.dccsoft.srytd.daos.ProcessDao;

public class FileBuilder {
	
	private static Logger logger = LoggerFactory.getLogger(FileBuilder.class);
	
	private ProcessDao processDao = new ProcessDao();
			
	public void start(Date from, String username) {
		MDC.put("user", username);
		long startTime = System.currentTimeMillis();
		// TODO . Marcar proceso como iniciado
		Long processId = processDao.create(from, username);
		logger.info(format("[%d] Starting process for date %tc", processId, from));
		
		// TODO . Leer datos de campo
		// TODO . Leer mapeos de tags
		// TODO . Realizar validaciones
		// TODO . Generar txt (csv)
		// TODO . Persistir txt
		// TODO . Subir a FTPServer
		// TODO . Enviar mail de resultado
		
		long duration = (System.currentTimeMillis() - startTime);
		logger.info(format("[%d] Process finished after %d millis", processId, duration));
		MDC.clear();
	}
}
