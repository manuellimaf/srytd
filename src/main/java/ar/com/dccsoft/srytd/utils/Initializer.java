package ar.com.dccsoft.srytd.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.process.ProcessScheduler;
import ar.com.dccsoft.srytd.utils.hibernate.HibernateUtil;

public class Initializer implements ServletContextListener {

	private static Logger logger = LoggerFactory.getLogger(Initializer.class);

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			logger.info("Starting application...");
			logger.info("Loding configuration...");
			Config.init();
			logger.info("Initializing datasource connections...");
			HibernateUtil.init();
			logger.info("Initializing Job scheduler...");
			ProcessScheduler.init();
			logger.info("Done.");
		} catch (Throwable t) {
			logger.error("Error initializing application", t);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("Shutting down application...");
		logger.info("Closing hibernate sessions...");
		try {
			HibernateUtil.closeSessions();
			logger.info("Done.");
		} catch (Throwable t) {
			logger.error("Error shutting down application", t);
		}
	}

}
