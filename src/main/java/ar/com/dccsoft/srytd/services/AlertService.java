package ar.com.dccsoft.srytd.services;

import java.util.List;

import ar.com.dccsoft.srytd.utils.Config;

import com.google.common.collect.Lists;

public class AlertService {

	private MailService mailService = new MailService();

	public void sendAlert(Long errorId, String username) {
		String subject = Config.getAlertSubject();
		String body = String.format(Config.getAlertBody(), username, errorId);

		// TODO . Read recipients from datasource
		List<String> recipients = Lists.newArrayList();

		mailService.send(subject, body, recipients);
	}

}
