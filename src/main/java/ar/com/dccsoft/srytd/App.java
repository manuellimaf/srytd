package ar.com.dccsoft.srytd;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.utils.Config;
import ar.com.dccsoft.srytd.utils.hibernate.HibernateUtil;
import ar.com.dccsoft.srytd.utils.http.HttpServer;

public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	private static String DEFAULT_USER = "consola";

	public static void main(String[] args) {

		try {
			Config.init();
			HibernateUtil.init();
						HttpServer.start();

			Date from = DateUtils.parseDate("2015-08-27 11:01:35", "yyyy-MM-dd HH:mm:ss");
			from = DateUtils.truncate(from, Calendar.HOUR_OF_DAY);

//			new Processor().start(from, DEFAULT_USER);
		} catch (Throwable t) {
			logger.error("Error found", t);
		} finally {
			// TODO . close on app shutdown
			// HibernateUtil.closeSessions();
		}

	}
}
