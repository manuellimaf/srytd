package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.MappedFieldValueDao;
import ar.com.dccsoft.srytd.model.Device;
import ar.com.dccsoft.srytd.model.FieldValue;
import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.utils.ui.Page;

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

		// This prevents errors when cloning bean with null properties
		BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
		
		return transactional(MySQL, (s) -> {
			List<MappedFieldValue> result = Lists.newLinkedList();
			int mappedCount = 0;
			for (FieldValue fieldValue : fieldValues) {

				MappedFieldValue mapped = new MappedFieldValue();

				try {
					BeanUtils.copyProperties(mapped, fieldValue);
				} catch (Exception e) {
					// This should never happen, it's almost the same object.
					logger.error(String.format("Error cloning field value id: %d", fieldValue.getId()), e);
				}

				String tag = map.get(fieldValue.getDeviceId());
				if(StringUtils.isNotBlank(tag)) {
					mappedCount++;
					mapped.setTag(tag);
				}
				mapped.setProcess(process);
				mapped.setId(null);

				dao.save(mapped);
				result.add(mapped);
			}
			logger.info(String.format("%d values mapped out of %d", mappedCount, result.size()));
			return result;
		});

	}

	public List<MappedFieldValue> safetlyGetValuesForProcess(Process process) {
		return tryAndInform("Error reading values for process", () -> {
			return getValuesForProcess(process.getId());
		});
	}

	public List<MappedFieldValue> getValuesForProcess(Long processId) {
		return transactional(MySQL, (session) -> {
			return dao.filterByProcessId(processId);
		});
	}

	public Page getPage(Long processId, Integer start, Integer limit) {
		return transactional(MySQL, (session) -> {
			List<MappedFieldValue> elems = dao.getPageForProcessId(processId, start, limit);
			Long total = dao.countAllForProcessId(processId);
			return new Page(elems, total);
		});
	}
}
