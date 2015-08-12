package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static java.lang.String.format;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.FieldValueDao;
import ar.com.dccsoft.srytd.model.FieldValue;

public class FieldValueService {

	private static Logger logger = LoggerFactory.getLogger(FieldValueService.class);
	private FieldValueDao dao = new FieldValueDao();

	public List<FieldValue> readOneHourValues(Date from) {
		return tryAndInform("Error reading field values", () -> {
			Date to = DateUtils.addHours(from, 1);
			logger.info(format("Reading field values for: %tc-%tc", from, to));
			List<FieldValue> fieldValues = dao.readFieldValues(from, to);
			logger.info(format("%d field values read", fieldValues.size()));
			return fieldValues;
		});
	}

}
