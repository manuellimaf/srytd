package ar.com.dccsoft.srytd.services;

import java.util.Arrays;
import java.util.List;

import ar.com.dccsoft.srytd.daos.AppPropertyDao;
import ar.com.dccsoft.srytd.model.AppProperty;

import com.google.common.collect.Lists;

public class AppPropertyService {

	private static final String ALERTS_RECIPIENTS = "alerts_recipients";
	private static final String COMPANY_ID = "company_id";
	private static final String FACILITY_ID = "facility_id";
	private static final String FTP_SERVER = "ftp_server";
	private static final String FTP_PORT = "ftp_port";
	private static final String FTP_USER = "ftp_user";
	private static final String FTP_PASSWORD = "ftp_password";

	private AppPropertyDao dao = new AppPropertyDao();

	public List<String> getAlertsRecipients() {
		AppProperty prop = dao.getProperty(ALERTS_RECIPIENTS);
		if (prop != null) {
			return Arrays.asList(prop.getValue().split(","));
		}
		return Lists.newArrayList();
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
}
