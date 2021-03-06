package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.api.MyObjectMapperProvider.STANDARD_FORMAT;
import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;
import static java.lang.String.format;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.ManualValueDTO;
import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.daos.MappedFieldValueDao;
import ar.com.dccsoft.srytd.model.DeviceMapping;
import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.model.Process;

import com.google.common.collect.Lists;

public class ManualFieldValueService {

	private static Logger logger = LoggerFactory.getLogger(ManualFieldValueService.class);
	private DeviceMappingService deviceService = new DeviceMappingService();
	private MappedFieldValueDao dao = new MappedFieldValueDao();

	public List<MappedFieldValue> readOneHourValues(Date from) {
		return tryAndInform("Error reading manual field values", () -> {
			return transactional(MySQL, (session) -> {
				Date to = DateUtils.addHours(from, 1);
				logger.info(format("Reading manual values for: %tY-%tm-%td (%tH:%tM - %tH:%tM)", from, from, from, from, from, to, to));
				List<MappedFieldValue> manualValues = dao.readManualValues(from, to);
				logger.info(format("%d manual values read", manualValues.size()));
				return manualValues;
			});
		});
	}

	public Page getPage(Integer start, Integer limit) {
		return transactional(MySQL, (session) -> {
			List<MappedFieldValue> elems = dao.getPageForManualValues(start, limit);
			Long total = dao.countAllManualValues();
			return new Page(asDtoList(elems), total);
		});
	}

	private List<ManualValueDTO> asDtoList(List<MappedFieldValue> elems) {
		List<ManualValueDTO> result = Lists.newLinkedList();
		for(MappedFieldValue value : elems) {
			ManualValueDTO dto = new ManualValueDTO();
			try {
				BeanUtils.copyProperties(dto, value);
				String[] datetime = STANDARD_FORMAT.format(value.getTimestamp()).split(" ");
				dto.setValueDate(datetime[0]);
				dto.setValueTime(datetime[1]);
			} catch (Exception e) {
				throw new RuntimeException("Error generating DTO from manual field value", e);
			} 
			result.add(dto);
		}
		return result;
	}

	public MappedFieldValue find(Long id) {
		return transactional(MySQL, (session) -> dao.findManualValue(id));
	}

	public void deleteManualValue(Long id) {
		transactional(MySQL, (session) -> {
			dao.delete(find(id));
			return null;
		});
	}

	public void createManualValue(ManualValueDTO dto, String username) {
		transactional(MySQL, (session) -> {
			
			MappedFieldValue mapped = new MappedFieldValue();
			copy(dto, mapped);
			mapped.setDateCreated(new Date());
			mapped.setCreatedBy(username);
			dao.save(mapped);
			
			return null;
		});
		
	}

	public void updateManualValue(ManualValueDTO dto) {
		transactional(MySQL, (session) -> {
			MappedFieldValue mapped = dao.findManualValue(dto.getId());
			copy(dto, mapped);
			dao.udpate(mapped);
			return null;
		});
	}

	public void update(List<MappedFieldValue> manualValues, Process process) {
		tryAndInform("Error updating manual values", () -> {
			transactional(MySQL, (session) -> {
				for (MappedFieldValue v : manualValues) {
					v.setProcess(process);
					dao.udpate(v);
				}

				return null;
			});
			return null;
		});
	}

	private void copy(ManualValueDTO dto, MappedFieldValue mapped) {
		// This prevents errors when cloning bean with null properties
		BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);

		try {
			BeanUtils.copyProperties(mapped, dto);

			DeviceMapping mapping = deviceService.getMappingForDevice(dto.getDeviceId());
			mapped.setCode(mapping.getCode());
			
			mapped.setValueType("M");
			mapped.setTimestamp(parse(dto.getValueDate(), dto.getValueTime(), "yyyy-MM-dd HH:mm"));
		} catch (Exception e) {
			throw new RuntimeException("Error cloning value from DTO to MappingFieldValue", e);
		}
	}

	private Date parse(String dateStr, String timeStr, String... format) {
		try {
			return DateUtils.parseDate(dateStr + " " + timeStr, format);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date and time", e);
		}
	}

}
