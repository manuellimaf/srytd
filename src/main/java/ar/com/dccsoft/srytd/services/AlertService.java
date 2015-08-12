package ar.com.dccsoft.srytd.services;

import java.util.List;

import ar.com.dccsoft.srytd.utils.Config;

public class AlertService {

	private MailService mailService = new MailService();
	private AppPropertyService propertyService = new AppPropertyService();

	public void sendAlert(Long errorId, String username) {
		String subject = Config.getAlertSubject();
		String body = String.format(Config.getAlertBody(), username, errorId);
		List<String> recipients = propertyService.getAlertsRecipients();

		mailService.send(subject, body, recipients);
	}

}
