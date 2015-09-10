package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.SQLSERVER;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;
import static java.lang.String.format;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.FieldValueDao;
import ar.com.dccsoft.srytd.daos.TagValueDao;
import ar.com.dccsoft.srytd.model.FieldValue;
import ar.com.dccsoft.srytd.model.TagValue;

public class FieldValueService {

	private static Logger logger = LoggerFactory.getLogger(FieldValueService.class);
	private FieldValueDao dao = new FieldValueDao();
	private TagValueDao tagDao = new TagValueDao();

	public List<FieldValue> readOneHourValues(Date from) {
		return tryAndInform("Error reading field values", () -> {
			return transactional(SQLSERVER, (session) -> {
				Date to = DateUtils.addHours(from, 1);
				logger.info(format("Reading field values for: %tY-%tm-%td (%tH:%tM - %tH:%tM)", from, from, from, from, from, to, to));

				List<TagValue> tagValues = readOneHourValues(from, "");
				
				FieldValue fv = new FieldValue();
				fv.setDeviceId("");
				fv.setTimestamp(from);
				fv.setValueType("A");
				fv.setId(0L);
//				List<FieldValue> fieldValues = Lists.newArrayList(fv);
				
				List<FieldValue> fieldValues = dao.readFieldValues(from, to);
				logger.info(format("%d field values read", fieldValues.size()));
				return fieldValues;
			});
		});
	}

	public List<TagValue> readOneHourValues(Date from, String tag) {
		return tryAndInform("Error reading tag values", () -> {
			return transactional(SQLSERVER, (session) -> {
				Date to = DateUtils.addHours(from, 1);
				// TODO - tagname
				List<TagValue> tagValues = tagDao.readTagValue(from, to, "");
				logger.info(format("%d tag values read", tagValues.size()));
				return tagValues;
			});
		});
	}
}
