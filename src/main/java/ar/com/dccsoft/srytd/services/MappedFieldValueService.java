package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;
import static java.lang.String.format;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.MappedFieldValueDao;
import ar.com.dccsoft.srytd.model.Device;
import ar.com.dccsoft.srytd.model.FieldValue;
import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.model.Process;

import com.google.common.collect.Lists;

public class MappedFieldValueService {

	private static Logger logger = LoggerFactory.getLogger(MappedFieldValueService.class);
	private MappedFieldValueDao dao = new MappedFieldValueDao();

	public List<MappedFieldValue> mapAndSave(Process process, List<FieldValue> fieldValues, List<Device> mappings) {

		logger.info("Mapping field values devices names to tags");
		/*
		 * Creates a Map from a List: 
		 * First, convert list to stream in order to apply collect method 
		 * Second, use toMap function: 
		 * 		- keys are generated applying getName method to every mapping 
		 * 		- values are generated applying getTag to every mapping
		 */
		Map<String, String> map = mappings.stream().collect(Collectors.toMap(Device::getName, (mapping) -> mapping.getTag()));

		return transactional(MySQL, (s) -> {
			List<MappedFieldValue> result = Lists.newLinkedList();
			for (FieldValue fieldValue : fieldValues) {
				MappedFieldValue mapped = new MappedFieldValue();

				try {
					BeanUtils.copyProperties(mapped, fieldValue);
				} catch (Exception e) {
					// This should never happen, it's almost the same object.
				logger.error(String.format("Error cloning field value id: %d", fieldValue.getId()), e);
			}

			mapped.setProcess(process);
			mapped.setTag(map.get(fieldValue.getDeviceId()));
			mapped.setId(null);

			dao.save(mapped);
			result.add(mapped);
		}
		logger.info(String.format("%d values mapped", result.size()));
		return result;
	}	);

	}

	public List<MappedFieldValue> readOneHourMaualValues(Date from) {
		return tryAndInform("Error reading manual values", () -> {
			return transactional(MySQL, (session) -> {
				Date to = DateUtils.addHours(from, 1);
				logger.info(format("Reading field values for: %tY-%tm-%td (%tH:%tM - %tH:%tM)", from, from, from, from, from, to, to));
				List<MappedFieldValue> mappings = dao.readManualValues(from, to);
				logger.info(format("%d manual values read", mappings.size()));
				return mappings;
			});
		});
	}

	public List<MappedFieldValue> getValuesForProcess(Long processId) {
		return transactional(MySQL, (session) -> {
			return dao.filterByProcessId(processId);
		});
	}
}
