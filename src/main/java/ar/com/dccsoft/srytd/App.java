package ar.com.dccsoft.srytd;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import ar.com.dccsoft.srytd.services.FileBuilder;
import ar.com.dccsoft.srytd.utils.HibernateUtil;

public class App {
	private static String DEFAULT_USER = "consola";
			
	public static void main(String[] args) {
		
		try {
			HibernateUtil.init();

			Date from = DateUtils.addHours(new Date(), -1);
			new FileBuilder().start(from, DEFAULT_USER);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			HibernateUtil.getSessionFactory().close();
		}
		
	}
}
