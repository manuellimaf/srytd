package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.ProcessAlertDao;
import ar.com.dccsoft.srytd.daos.ProcessDao;
import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessAlert;
import ar.com.dccsoft.srytd.model.ProcessResult;
import ar.com.dccsoft.srytd.utils.MDCUtils;
import ar.com.dccsoft.srytd.utils.MDCUtils.MDCKey;


public class ProcessAlertService {

	private static Logger logger = LoggerFactory.getLogger(ProcessAlertService.class);
	private ProcessAlertDao alertDao = new ProcessAlertDao();
	private NotificationsService alertService = new NotificationsService();
	private ProcessDao processDao = new ProcessDao();
	
	public ProcessAlert getAlert(Long id) {
		return transactional(MySQL, (session) -> alertDao.find(id));
	}

	public void handleError(Long errorId, String message, Throwable t) {
		logger.info("Handling error " + errorId);
		String username = MDCUtils.currentUser();
		transactional(MySQL, (session) -> {
			ProcessAlert error = buildAlert(errorId, message, username, ExceptionUtils.getStackTrace(t));
			alertDao.saveAlert(error);
			logger.info("Error persisted in database with id: " + error.getId());

			Long processId = Long.valueOf(MDCUtils.get(MDCKey.PROCESS_ID));
			Process process = processDao.find(processId); 
			ProcessResult result = process.getResult();
			result.setError(error);
			processDao.update(process);
			
			return error;
		});

		alertService.sendAlert(errorId, username);
	}

	private ProcessAlert buildAlert(Long errorId, String message, String username, String description) {
		ProcessAlert alert = new ProcessAlert();
		alert.setIdentifier(errorId.toString());
		alert.setMessage(message);
		alert.setDescription(description);
		alert.setUsername(username);
		return alert;
	}

}
