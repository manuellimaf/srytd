package ar.com.dccsoft.srytd.utils;

import org.slf4j.MDC;

public class MDCUtils {

	public static void put(MDCKey key, String value) {
		MDC.put(key.name(), value);
	}

	public static String get(MDCKey key, String defaultValue) {
		try {
			String res = MDC.get(key.name());
			return res == null ? defaultValue : res;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static String get(MDCKey key) {
		return MDC.get(key.name());
	}

	public static String currentUser() {
		return get(MDCKey.USER, "unknown");
	}

	public static void clear() {
		MDC.clear();
	}

	public enum MDCKey {
		USER, PROCESS_ID
	}

}
