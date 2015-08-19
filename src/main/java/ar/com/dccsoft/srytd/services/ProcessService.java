package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;

import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.ProcessDao;

public class ProcessService {

	private static Logger logger = LoggerFactory.getLogger(ProcessService.class);
	private ProcessDao processDao = new ProcessDao();

	public Long create(Date from, String username) {
		String errorMessage = String.format("Error creating process for date %tc", from);
		return tryAndInform(errorMessage, () -> {
			return processDao.create(from, username).getId();
		});
	}

	public void saveFile(Long processId, InputStream is) {
		try {
			String data = IOUtils.toString(is);
			processDao.saveFile(processId, data);
		} catch (Throwable t) {
			logger.error("Error persisting generated file to database", t);
		}

	}
}
