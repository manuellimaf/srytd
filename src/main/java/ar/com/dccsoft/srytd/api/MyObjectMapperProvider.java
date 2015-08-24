package ar.com.dccsoft.srytd.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {

	private ObjectMapper defaultObjectMapper;
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public MyObjectMapperProvider() {
		defaultObjectMapper = createDefaultMapper();
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return defaultObjectMapper;
	}

	private static ObjectMapper createDefaultMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.configure(SerializationFeature.INDENT_OUTPUT, true);
		result.setDateFormat(df);

		return result;
	}
}