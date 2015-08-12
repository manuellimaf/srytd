package ar.com.dccsoft.srytd;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.FileBuilder;
import ar.com.dccsoft.srytd.utils.Config;
import ar.com.dccsoft.srytd.utils.hibernate.HibernateUtil;

public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	private static String DEFAULT_USER = "consola";

	public static void main(String[] args) {

		try {
			Config.init();
			HibernateUtil.init();

			Date from = DateUtils.addHours(new Date(), -1);
			from = DateUtils.truncate(from, Calendar.HOUR_OF_DAY);

			new FileBuilder().start(from, DEFAULT_USER);
		} catch (Throwable t) {
			logger.error("Error found", t);
		} finally {
			HibernateUtil.closeSessions();
		}

	}
}
