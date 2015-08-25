package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.util.Arrays;
import java.util.List;

import ar.com.dccsoft.srytd.daos.AppPropertyDao;
import ar.com.dccsoft.srytd.model.AppProperty;

import com.google.common.collect.Lists;

// FIXME - Hacer que tenga un mapa con las propiedades en memoria y que se puedan recargar
public class AppPropertyService {

	private static final String ALERTS_RECIPIENTS = "alerts_recipients";
	private static final String FINISH_RECIPIENTS = "finish_recipients";
	private static final String COMPANY_ID = "company_id";
	private static final String FACILITY_ID = "facility_id";
	private static final String FTP_SERVER = "ftp_server";
	private static final String FTP_PORT = "ftp_port";
	private static final String FTP_USER = "ftp_user";
	private static final String FTP_PASSWORD = "ftp_password";

	private AppPropertyDao dao = new AppPropertyDao();

	public void updateProperties(PropertiesDTO dto) {
		// TODO . validate

		transactional(MySQL, (s) -> {
			dao.upsert(COMPANY_ID, dto.getCompanyCode());
			dao.upsert(FACILITY_ID, dto.getFacilityCode());
			dao.upsert(FTP_SERVER, dto.getIp());
			dao.upsert(FTP_PORT, dto.getPort().toString());
			dao.upsert(FTP_USER, dto.getFtpUser());
			dao.upsert(FTP_PASSWORD, dto.getFtpPassword());
			dao.upsert(ALERTS_RECIPIENTS, dto.getAlertEmails());
			dao.upsert(FINISH_RECIPIENTS, dto.getNotificationEmails());
			return null;
		});
	}

	public List<String> getAlertsRecipients() {
		AppProperty prop = dao.getProperty(ALERTS_RECIPIENTS);
		return asRecipientsList(prop);
	}

	public String getAlertsRecipientsStr() {
		AppProperty prop = dao.getProperty(ALERTS_RECIPIENTS);
		return asStr(prop);
	}

	public List<String> getFinishEmailRecipients() {
		AppProperty prop = dao.getProperty(FINISH_RECIPIENTS);
		return asRecipientsList(prop);
	}

	public String getFinishEmailRecipientsStr() {
		AppProperty prop = dao.getProperty(FINISH_RECIPIENTS);
		return asStr(prop);
	}

	private List<String> asRecipientsList(AppProperty prop) {
		if (prop != null) {
			return Arrays.asList(prop.getValue().split(","));
		}
		return Lists.newArrayList();
	}

	private String asStr(AppProperty prop) {
		if (prop != null) {
			return prop.getValue().toString();
		}
		return "";
	}

	public String getCompanyId() {
		return dao.getProperty(COMPANY_ID).getValue();
	}

	public String getFacilityId() {
		return dao.getProperty(FACILITY_ID).getValue();
	}

	public FTPConfig getFTPConfig() {
		return new FTPConfig();
	}

	public class FTPConfig {
		private String server = dao.getProperty(FTP_SERVER).getValue();
		private Integer port = Integer.valueOf(dao.getProperty(FTP_PORT).getValue());
		private String username = dao.getProperty(FTP_USER).getValue();
		private String password = dao.getProperty(FTP_PASSWORD).getValue();

		public String getServer() {
			return server;
		}

		public Integer getPort() {
			return port;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}
	}

	public PropertiesDTO getPropertiesDTO() {
		PropertiesDTO dto = new PropertiesDTO();
		dto.setCompanyCode(getCompanyId());
		dto.setFacilityCode(getFacilityId());
		FTPConfig config = getFTPConfig();
		dto.setIp(config.getServer());
		dto.setPort(config.getPort());
		dto.setFtpUser(config.getUsername());
		dto.setFtpPassword(config.getPassword());
		dto.setAlertEmails(getAlertsRecipientsStr());
		dto.setNotificationEmails(getFinishEmailRecipientsStr());
		return dto;
	}

	public static class PropertiesDTO {
		private String companyCode;
		private String facilityCode;
		private String ip;
		private Integer port;
		private String ftpUser;
		private String ftpPassword;
		private String alertEmails;
		private String notificationEmails;

		public String getCompanyCode() {
			return companyCode;
		}

		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}

		public String getFacilityCode() {
			return facilityCode;
		}

		public void setFacilityCode(String facilityCode) {
			this.facilityCode = facilityCode;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public String getFtpUser() {
			return ftpUser;
		}

		public void setFtpUser(String ftpUser) {
			this.ftpUser = ftpUser;
		}

		public String getFtpPassword() {
			return ftpPassword;
		}

		public void setFtpPassword(String ftpPassword) {
			this.ftpPassword = ftpPassword;
		}

		public String getAlertEmails() {
			return alertEmails;
		}

		public void setAlertEmails(String alertEmails) {
			this.alertEmails = alertEmails;
		}

		public String getNotificationEmails() {
			return notificationEmails;
		}

		public void setNotificationEmails(String notificationEmails) {
			this.notificationEmails = notificationEmails;
		}

	}

}
