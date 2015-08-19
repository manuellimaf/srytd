package ar.com.dccsoft.srytd.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.utils.Config;

public class NotificationsService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationsService.class);
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

	public void sendFinishMessage(String dateStr, byte[] data, String fileName) {
		String subject = String.format(Config.getFinishEmailSubject(), dateStr);
		String body = String.format(Config.getFinishEmailBody(), dateStr);
		List<String> recipients = propertyService.getFinishEmailRecipients();

		if (!recipients.isEmpty()) {
			mailService.send(subject, body, recipients, data, fileName, "text/plain");
		} else {
			logger.warn("No recipients found for process finish email.");
		}
	}

}
