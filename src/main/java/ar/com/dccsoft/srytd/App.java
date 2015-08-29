package ar.com.dccsoft.srytd;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.Processor;
import ar.com.dccsoft.srytd.utils.Config;
import ar.com.dccsoft.srytd.utils.hibernate.HibernateUtil;
import ar.com.dccsoft.srytd.utils.http.HttpServer;

public class App {
	private static final String SERVER = "server";

	private static Logger logger = LoggerFactory.getLogger(App.class);

	private static String DEFAULT_USER = "consola";

	public static void main(String[] args) {

		String mode = args.length == 0 ? SERVER : args[0];

		Config.init();
		HibernateUtil.init();

		if (mode == SERVER) {
			HttpServer.start();
			System.out.close();
			System.err.close();
		} else {
			int exitCode = 0;
			try {
				Date from = DateUtils.parseDate("2015-08-27 11:01:35", "yyyy-MM-dd HH:mm:ss");
				from = DateUtils.truncate(from, Calendar.HOUR_OF_DAY);
				new Processor().start(from, DEFAULT_USER);
			} catch (Throwable t) {
				// Errors have been handled
				exitCode = 1;
			} finally {
				 HibernateUtil.closeSessions();
				 System.exit(exitCode);
			}

		}

	}
}
