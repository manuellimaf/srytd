package ar.com.dccsoft.srytd.utils;

import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

	private static Logger logger = LoggerFactory.getLogger(Config.class);

	private static String sqlServerUrl = null;
	private static String sqlServerUser = null;
	private static String sqlServerPassword = null;
	private static String mysqlUrl = null;
	private static String mysqlUser = null;
	private static String mysqlPassword = null;
	private static String mailUser = null;
	private static String mailPassword = null;
	private static Properties mailProperties = null;
	private static String alertSubject = null;
	private static String alertBody = null;
	private static String finishEmailSubject = null;
	private static String finishEmailBody = null;

	public static void init() {
		Configuration config;
		try {
			config = new XMLConfiguration("config.xml");
		} catch (ConfigurationException e) {
			throw new RuntimeException("Error loading configuration file", e);
		}

		sqlServerUrl = config.getString("sqlserver-url");
		sqlServerUser = config.getString("sqlserver-user");
		sqlServerPassword = config.getString("sqlserver-password");
		mysqlUrl = config.getString("mysql-url");
		mysqlUser = config.getString("mysql-user");
		mysqlPassword = config.getString("mysql-password");

		mailProperties = new Properties();
		mailProperties.put("mail.from", config.getString("mail-from"));
		mailProperties.put("mail.smtp.host", config.getString("mail-smtp-host"));
		mailProperties.put("mail.smtp.port", config.getInt("mail-smtp-port"));
		mailUser = config.getString("mail-user");
		mailPassword = config.getString("mail-password");

		alertSubject = config.getString("alert-subject");
		alertBody = config.getString("alert-body");
		finishEmailSubject = config.getString("finish-subject");
		finishEmailBody = config.getString("finish-body");
	}

	public static String getSqlServerUrl() {
		return sqlServerUrl;
	}

	public static String getSqlServerUser() {
		return sqlServerUser;
	}

	public static String getSqlServerPassword() {
		return sqlServerPassword;
	}

	public static String getMysqlUrl() {
		return mysqlUrl;
	}

	public static String getMysqlUser() {
		return mysqlUser;
	}

	public static String getMysqlPassword() {
		return mysqlPassword;
	}

	public static String getMailUser() {
		return mailUser;
	}

	public static String getMailPassword() {
		return mailPassword;
	}

	public static Properties getMailProperties() {
		return mailProperties;
	}

	public static String getAlertSubject() {
		return alertSubject;
	}

	public static String getAlertBody() {
		return alertBody;
	}

	public static String getFinishEmailSubject() {
		return finishEmailSubject;
	}

	public static String getFinishEmailBody() {
		return finishEmailBody;
	}
}
