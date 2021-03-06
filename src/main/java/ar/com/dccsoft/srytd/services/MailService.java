package ar.com.dccsoft.srytd.services;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import ar.com.dccsoft.srytd.utils.Config;

public class MailService {

	private static Logger log = LoggerFactory.getLogger(MailService.class);

	public void send(String subject, String body, List<String> recipients) {
		if(recipients.isEmpty()) {
			log.warn("Trying to send email to an empty list of recipients!");
			return;
		}
		
		InternetAddress[] to = createInternetAddresses(recipients);
		Message message = createMessage(subject, body, to);
		send(message);
	}

	public void send(String subject, String body, List<String> recipients, byte[] attachment, String attachmentName, String attachmentType) {
		if(recipients.isEmpty()) {
			log.warn("Trying to send email to an empty list of recipients!");
			return;
		}

		InternetAddress[] to = createInternetAddresses(recipients);
		Multipart multipart = createMultipartBody(body, attachment, attachmentName, attachmentType);
		Message message = createMultipartMessage(subject, multipart, to);
		send(message);
	}

	private void send(Message message) {
		try {
			log.debug("About to send email");
			Transport.send(message);
			log.debug("Email sent");
		} catch (MessagingException e) {
			log.error("Error sending mail message", e);
		}
	}

	private Multipart createMultipartBody(String body, byte[] attachment, String attachmentName, String attachmentType) {
		Multipart multipart = new MimeMultipart();
		MimeBodyPart bodyPart = new MimeBodyPart();
		try {
			bodyPart.setText(body);
			multipart.addBodyPart(bodyPart);

			MimeBodyPart attachmentPart = new MimeBodyPart();
			DataSource source = new ByteArrayDataSource(attachment, attachmentType);
			attachmentPart.setDataHandler(new DataHandler(source));
			attachmentPart.setFileName(attachmentName);
			multipart.addBodyPart(attachmentPart);
			return multipart;
		} catch (MessagingException e) {
			throw new RuntimeException("Error creating multipart body", e);
		}

	}

	private Message createMultipartMessage(String subject, Multipart multipartBody, InternetAddress[] to) {
		Session session = mailSession();
		Message msg = new MimeMessage(session);
		try {
			msg.setSubject(subject);
			msg.setRecipients(Message.RecipientType.TO, to);
			msg.setContent(multipartBody);
			return msg;
		} catch (MessagingException e) {
			throw new RuntimeException("Error creating multipart mail message", e);
		}
	}

	private Message createMessage(String subject, String body, InternetAddress[] to) {
		Session session = mailSession();
		Message message = new MimeMessage(session);
		try {
			message.setSubject(subject);
			message.setContent(body, "text/plain");
			message.setRecipients(Message.RecipientType.TO, to);
		} catch (MessagingException e) {
			throw new RuntimeException("Error creating mail message", e);
		}

		return message;
	}

	private InternetAddress[] createInternetAddresses(List<String> recipients) {
		List<InternetAddress> to = Lists.transform(recipients, recipient -> createInternetAddress(recipient));
		return to.toArray(new InternetAddress[0]);
	}

	private InternetAddress createInternetAddress(String recipient) {
		try {
			return new InternetAddress(recipient);
		} catch (AddressException e) {
			throw new RuntimeException(String.format("Invalid mail address '%s'", recipient), e);
		}
	}

	private Session mailSession() {
		Properties mailProperties = Config.getMailProperties();
		String user = Config.getMailUser();
		String password = Config.getMailPassword();
		Session session = Session.getDefaultInstance(mailProperties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		return session;
	}

}