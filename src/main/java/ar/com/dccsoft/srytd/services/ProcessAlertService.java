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
import ar.com.dccsoft.srytd.model.ProcessStatus;
import ar.com.dccsoft.srytd.utils.MDCUtils;
import ar.com.dccsoft.srytd.utils.MDCUtils.MDCKey;

// TODO . This class has lots of copy/pasted code. Refactor required!
public class ProcessAlertService {

	private static Logger logger = LoggerFactory.getLogger(ProcessAlertService.class);
	private NotificationsService notificationService = new NotificationsService();
	private MappedFieldValueService mappingsService = new MappedFieldValueService();
	private ProcessAlertDao alertDao = new ProcessAlertDao();
	private ProcessDao processDao = new ProcessDao();
	
	public ProcessAlert getAlert(Long id) {
		return transactional(MySQL, (session) -> alertDao.find(id));
	}

	public void addWarning(String message, String description) {
		Long errorId = System.currentTimeMillis();
		logger.info("Registering warning " + errorId);
		String username = MDCUtils.currentUser();
		transactional(MySQL, (session) -> {
			ProcessAlert warning = buildAlert(errorId, message, username, description);
			addToProcess(warning);
			logger.info("Warning persisted in database with");
			
			return warning;
		});
	}
	
	public void handleError(Long errorId, String message, Throwable t) {
		logger.info("Handling error " + errorId);
		String username = MDCUtils.currentUser();
		try {
			transactional(MySQL, (session) -> {
				ProcessAlert error = buildAlert(errorId, message, username, ExceptionUtils.getStackTrace(t));
				alertDao.saveAlert(error);
				logger.info("Process alert persisted in database with id: " + error.getId());

				bindToProcess(error);

				return error;
			});
		} finally {
			// Alerts must be sent, no matter what
			notificationService.sendAlert(errorId, username);
		}
	}

	private void addToProcess(ProcessAlert warning) {
		Long processId = Long.valueOf(MDCUtils.get(MDCKey.PROCESS_ID));
		Process process = processDao.find(processId); 
		ProcessResult result = process.getResult();
		result.getWarnings().add(warning);
		processDao.update(process);
	}

	private void bindToProcess(ProcessAlert error) {
		Long processId = Long.valueOf(MDCUtils.get(MDCKey.PROCESS_ID));
		logger.info("Saving error for process " + processId);
		Process process = processDao.find(processId); 
		process.setStatus(ProcessStatus.ERROR);
		
		long mappingsCount = mappingsService.getValuesForProcess(processId).size();
		ProcessResult result = process.getResult();
		result.setError(error);
		result.setStatus(ProcessStatus.ERROR);
		result.setUnsentValues(mappingsCount);	
		processDao.update(process);
		logger.info("Process " + processId + " result = " + result.getStatus().toString());
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
