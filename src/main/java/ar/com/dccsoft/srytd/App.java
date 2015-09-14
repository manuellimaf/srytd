package ar.com.dccsoft.srytd;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.process.ProcessScheduler;
import ar.com.dccsoft.srytd.services.process.Processor;
import ar.com.dccsoft.srytd.utils.Config;
import ar.com.dccsoft.srytd.utils.hibernate.HibernateUtil;
import ar.com.dccsoft.srytd.utils.http.HttpServer;

public class App {
	private static final String SERVER = "server";

	private static Logger logger = LoggerFactory.getLogger(App.class);

	private static String USER = "consola";

	public static void main(String[] args) {

		String mode = args.length == 0 ? SERVER : args[0];
		logger.info("Loading in mode " + mode);
		int exitCode = 0;

		try {
			Config.init();
			HibernateUtil.init();
			ProcessScheduler.init();

			if (mode.equals(SERVER)) {
				HttpServer.start();
			} else {
				Date from = DateUtils.parseDate(args[1], "yyyy-MM-dd HH:mm:ss");
				from = DateUtils.truncate(from, Calendar.HOUR_OF_DAY);
				new Processor().start(from, USER);

			}
		} catch (Throwable t) {
			t.printStackTrace();
			exitCode = 1;
		} finally {
			HibernateUtil.closeSessions();
			System.exit(exitCode);
		}

	}
}
