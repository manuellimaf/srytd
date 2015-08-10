package ar.com.dccsoft.srytd.services;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.utils.Config;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class MailService {

	private static Logger log = LoggerFactory.getLogger(MailService.class);

	public void send(String subject, String body, List<String> recipients) {
		InternetAddress[] to = createInternetAddresses(recipients);
		Message message = createMessage(subject, body, to);
		send(message);
	}

	public void send(String subject, String body, List<String> recipients, File attachment) {
		InternetAddress[] to = createInternetAddresses(recipients);
		Multipart multipart = createMultipartBody(body, attachment);
		Message message = createMultipartMessage(subject, multipart, to);
		send(message);
	}

	private void send(Message message) {
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			log.error("Error sending mail message", e);
		}
	}

	private Multipart createMultipartBody(String body, File attachment) {
		Multipart multipart = new MimeMultipart();
		MimeBodyPart bodyPart = new MimeBodyPart();
		try {
			bodyPart.setText(body);
			multipart.addBodyPart(bodyPart);

			MimeBodyPart attachmentPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachment);
			attachmentPart.setDataHandler(new DataHandler(source));
			attachmentPart.setFileName(attachment.getName());
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
		InternetAddress[] to = Lists.transform(recipients, new Function<String, InternetAddress>() {
			@Override
			public InternetAddress apply(String recipient) {
				return createInternetAddress(recipient);
			}

		}).toArray(new InternetAddress[0]);
		return to;
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