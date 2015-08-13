package ar.com.dccsoft.srytd.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.utils.Config;

public class AlertService {

	private static final Logger logger = LoggerFactory.getLogger(AlertService.class);
	private MailService mailService = new MailService();
	private AppPropertyService propertyService = new AppPropertyService();

	public void sendAlert(Long errorId, String username) {
		String subject = Config.getAlertSubject();
		String body = String.format(Config.getAlertBody(), username, errorId);
		List<String> recipients = propertyService.getAlertsRecipients();

		if (!recipients.isEmpty()) {
			mailService.send(subject, body, recipients);
		} else {
			logger.warn("No recipients found for mail alerts.");
		}
	}

}
