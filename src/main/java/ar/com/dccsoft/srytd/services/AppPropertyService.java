package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

import ar.com.dccsoft.srytd.api.dto.PropertiesDTO;
import ar.com.dccsoft.srytd.daos.AppPropertyDao;
import ar.com.dccsoft.srytd.model.AppProperty;

// FIXME - Hacer que tenga un mapa con las propiedades en memoria y que se puedan recargar
public class AppPropertyService {

	private static final String RECIPIENTS_DELIMITER = ",";
	private static final String ALERTS_RECIPIENTS = "alerts_recipients";
	private static final String FINISH_RECIPIENTS = "finish_recipients";
	private static final String COMPANY_ID = "company_id";
	private static final String FACILITY_ID = "facility_id";
	private static final String FTP_SERVER = "ftp_server";
	private static final String FTP_PORT = "ftp_port";
	private static final String FTP_USER = "ftp_user";
	private static final String FTP_PASSWORD = "ftp_password";
	private static final String FTP_TYPE = "ftp_type";

	private AppPropertyDao dao = new AppPropertyDao();

	public void updateProperties(PropertiesDTO dto) {
		transactional(MySQL, (s) -> {
			dao.upsert(COMPANY_ID, dto.getCompanyCode());
			dao.upsert(FACILITY_ID, dto.getFacilityCode());
			dao.upsert(FTP_SERVER, dto.getIp());
			dao.upsert(FTP_PORT, dto.getPort().toString());
			dao.upsert(FTP_USER, dto.getFtpUser());
			dao.upsert(FTP_PASSWORD, dto.getFtpPassword());
			dao.upsert(FTP_TYPE, dto.getFtpType());
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
		if (prop != null && StringUtils.isNotBlank(prop.getValue())) {
			return Arrays.asList(prop.getValue().split(RECIPIENTS_DELIMITER));
		}
		return Lists.newArrayList();
	}

	private String asStr(AppProperty prop) {
		if (prop != null) {
			return prop.getValue().toString();
		}
		return "";
	}

	private Integer asInt(AppProperty prop) {
		if (prop != null) {
			return Integer.valueOf(prop.getValue());
		}
		return null;
	}

	public String getCompanyId() {
		return asStr(dao.getProperty(COMPANY_ID));
	}

	public String getFacilityId() {
		return asStr(dao.getProperty(FACILITY_ID));
	}

	public FTPConfig getFTPConfig() {
		return new FTPConfig();
	}

	public class FTPConfig {
		private String server = asStr(dao.getProperty(FTP_SERVER));
		private Integer port = asInt(dao.getProperty(FTP_PORT));
		private String username = asStr(dao.getProperty(FTP_USER));
		private String password = asStr(dao.getProperty(FTP_PASSWORD));
		private String type = asStr(dao.getProperty(FTP_TYPE));

		public String getType() {
			return type;
		}
		
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
		dto.setFtpType(config.getType());
		dto.setAlertEmails(getAlertsRecipientsStr());
		dto.setNotificationEmails(getFinishEmailRecipientsStr());
		return dto;
	}


}
