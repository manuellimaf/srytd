package ar.com.dccsoft.srytd.utils.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.utils.Config;

public class HibernateUtil {
	// Logger creation MUST be the first statement (NPE Otherwise)
	private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

	private static final SessionFactory sqlSrvSessionFactory = buildSQLServerSessionFactory();
	private static final SessionFactory mysqlSessionFactory = buildMySQLSessionFactory();

	public static void init() {
		logger.info("Hibernate initialization finished");
	}

	public static void closeSessions() {
		sqlSrvSessionFactory.close();
		mysqlSessionFactory.close();
	}

	static Session currentSQLServerSession() {
		return sqlSrvSessionFactory.getCurrentSession();
	}

	static Session currentMySQLSession() {
		return mysqlSessionFactory.getCurrentSession();
	}

	private static SessionFactory buildSQLServerSessionFactory() {
		return buildSessionFactory("hibernate/hibernate-sqlserver.cfg.xml", Config.getSqlServerUrl(), Config.getSqlServerUser(),
				Config.getSqlServerPassword());
	}

	private static SessionFactory buildMySQLSessionFactory() {
		return buildSessionFactory("hibernate/hibernate-mysql.cfg.xml", Config.getMysqlUrl(), Config.getMysqlUser(),
				Config.getMysqlPassword());
	}

	private static SessionFactory buildSessionFactory(String configFile, String url, String username, String password)
			throws ExceptionInInitializerError {
		logger.info("Initializing Hibernate using " + configFile);
		try {
			Configuration configuration = new Configuration().configure(configFile);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
					.applySetting("hibernate.connection.url", url).applySetting("hibernate.connection.username", username)
					.applySetting("hibernate.connection.password", password).build();
			return configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable ex) {
			logger.error("Initial SessionFactory creation failed", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

}