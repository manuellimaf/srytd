package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;
import static java.lang.String.format;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.daos.MappedFieldValueDao;
import ar.com.dccsoft.srytd.model.DeviceMapping;
import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.TagValue;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MappedFieldValueService {

	private static Logger logger = LoggerFactory.getLogger(MappedFieldValueService.class);
	private MappedFieldValueDao dao = new MappedFieldValueDao();

	public List<MappedFieldValue> map(Set<TagValue> fieldValues, List<DeviceMapping> deviceMappings) {
		return tryAndInform("Error mapping field values", () -> {

			Map<String, TagValue> tagValuesMap = toMap(fieldValues);
			List<MappedFieldValue> mappedFieldValues = Lists.newArrayList();
			for (DeviceMapping device : deviceMappings) {

				logger.info(format("Mapping field values for device %s", device.getName()));
				
				MappedFieldValue fv = buildMappedFieldValue(device, tagValuesMap);
				
				// Si tiene fecha es porque tiene al menos una medición, entonces, lo agrego a la lista
				if(fv.getTimestamp() != null) {
					mappedFieldValues.add(fv);
				}
			}

			logger.info(format("%d field values read", mappedFieldValues.size()));
			return mappedFieldValues;
		});
	}

	public MappedFieldValue buildMappedFieldValue(DeviceMapping device, Map<String, TagValue> tagValuesMap) {
		MappedFieldValue fv = new MappedFieldValue();
		fv.setDeviceId(device.getName());
		fv.setCode(device.getCode());
		fv.setValueType("A");

		applyForTag(tagValuesMap, device.getPresion(), fv, (tagValue) -> {
			fv.setPresion(tagValue.getValue());
			fv.setPresion_q(tagValue.getQuality());
		});
		applyForTag(tagValuesMap, device.getTemperatura(), fv, (tagValue) -> {
			fv.setTemperatura(tagValue.getValue());
			fv.setTemperatura_q(tagValue.getQuality());
		});
		applyForTag(tagValuesMap, device.getCaudal_horario(), fv, (tagValue) -> {
			fv.setCaudal_horario(tagValue.getValue());
			fv.setCaudal_horario_q(tagValue.getQuality());
		});
		applyForTag(tagValuesMap, device.getVolumen_bruto_acumulado(), fv, (tagValue) -> {
			fv.setVolumen_bruto_acumulado(tagValue.getValue());
			fv.setVolumen_bruto_acumulado_q(tagValue.getQuality());
		});
		applyForTag(tagValuesMap, device.getVolumen_neto_hoy(), fv, (tagValue) -> {
			fv.setVolumen_neto_hoy(tagValue.getValue());
			fv.setVolumen_neto_hoy_q(tagValue.getQuality());
		});
		applyForTag(tagValuesMap, device.getCaudal_horario_9300(), fv, (tagValue) -> {
			fv.setCaudal_horario_9300(tagValue.getValue());
			fv.setCaudal_horario_9300_q(tagValue.getQuality());
		});
		applyForTag(tagValuesMap, device.getVolumen_acumulado_9300(), fv, (tagValue) -> {
			fv.setVolumen_acumulado_9300(tagValue.getValue());
			fv.setVolumen_acumulado_9300_q(tagValue.getQuality());
		});
		applyForTag(tagValuesMap, device.getVolumen_desplazado(), fv, (tagValue) -> {
			fv.setVolumen_desplazado(tagValue.getValue());
			fv.setVolumen_desplazado_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getAltura_liquida(), fv, (tagValue) -> {
			fv.setAltura_liquida(tagValue.getValue());
			fv.setAltura_liquida_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getMf(), fv, (tagValue) -> {
			fv.setMf(tagValue.getValue());
			fv.setMf_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getCtl(), fv, (tagValue) -> {
			fv.setCtl(tagValue.getValue());
			fv.setCtl_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getCpl(), fv, (tagValue) -> {
			fv.setCpl(tagValue.getValue());
			fv.setCpl_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getFactor_k(), fv, (tagValue) -> {
			fv.setFactor_k(tagValue.getValue());
			fv.setFactor_k_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getPulsos_brutos(), fv, (tagValue) -> {
			fv.setPulsos_brutos(tagValue.getValue());
			fv.setPulsos_brutos_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getFcv(), fv, (tagValue) -> {
			fv.setFcv(tagValue.getValue());
			fv.setFcv_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getCtsh(), fv, (tagValue) -> {
			fv.setCtsh(tagValue.getValue());
			fv.setCtsh_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getPorcentaje_agua(), fv, (tagValue) -> {
			fv.setPorcentaje_agua(tagValue.getValue());
			fv.setPorcentaje_agua_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getPoder_calorifico(), fv, (tagValue) -> {
			fv.setPoder_calorifico(tagValue.getValue());
			fv.setPoder_calorifico_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getDensidad_relativa(), fv, (tagValue) -> {
			fv.setDensidad_relativa(tagValue.getValue());
			fv.setDensidad_relativa_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getCo2(), fv, (tagValue) -> {
			fv.setCo2(tagValue.getValue());
			fv.setCo2_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getN2(), fv, (tagValue) -> {
			fv.setN2(tagValue.getValue());
			fv.setN2_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getSh2(), fv, (tagValue) -> {
			fv.setSh2(tagValue.getValue());
			fv.setSh2_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getC1(), fv, (tagValue) -> {
			fv.setC1(tagValue.getValue());
			fv.setC1_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getC2(), fv, (tagValue) -> {
			fv.setC2(tagValue.getValue());
			fv.setC2_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getC3(), fv, (tagValue) -> {
			fv.setC3(tagValue.getValue());
			fv.setC3_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getIc4(), fv, (tagValue) -> {
			fv.setIc4(tagValue.getValue());
			fv.setIc4_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getNc4(), fv, (tagValue) -> {
			fv.setNc4(tagValue.getValue());
			fv.setNc4_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getIc5(), fv, (tagValue) -> {
			fv.setIc5(tagValue.getValue());
			fv.setIc5_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getNc5(), fv, (tagValue) -> {
			fv.setNc5(tagValue.getValue());
			fv.setNc5_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getC6(), fv, (tagValue) -> {
			fv.setC6(tagValue.getValue());
			fv.setC6_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getVolumen_seco(), fv, (tagValue) -> {
			fv.setVolumen_seco(tagValue.getValue());
			fv.setVolumen_seco_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getInicio_transac(), fv, (tagValue) -> {
			fv.setInicio_transac(tagValue.getValue());
			fv.setInicio_transac_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getFin_transac(), fv, (tagValue) -> {
			fv.setFin_transac(tagValue.getValue());
			fv.setFin_transac_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getVolumen_hoy_9300(), fv, (tagValue) -> {
			fv.setVolumen_hoy_9300(tagValue.getValue());
			fv.setVolumen_hoy_9300_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getDensidad(), fv, (tagValue) -> {
			fv.setDensidad(tagValue.getValue());
			fv.setDensidad_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getVolumen_bruto_hoy(), fv, (tagValue) -> {
			fv.setVolumen_bruto_hoy(tagValue.getValue());
			fv.setVolumen_bruto_hoy_q(tagValue.getQuality());
		});
		
		applyForTag(tagValuesMap, device.getVolumen_neto_acumulado(), fv, (tagValue) -> {
			fv.setVolumen_neto_acumulado(tagValue.getValue());
			fv.setVolumen_neto_acumulado_q(tagValue.getQuality());
		});
		
		return fv;
	}

	public void applyForTag(Map<String, TagValue> tagValuesMap, String tag, MappedFieldValue fv, TagValueProcessor processor) {
		if (StringUtils.isNotBlank(tag)) {
			TagValue tagValue = tagValuesMap.get(tag);
			if(tagValue != null) {
				processor.process(tagValue);
				fv.updateTimestamp(tagValue.getDatetime());
			}
		}
	}

	private Map<String, TagValue> toMap(Set<TagValue> fieldValues) {
		Map<String, TagValue> tagValuesMap = Maps.newHashMap();
		for(TagValue tv : fieldValues) {
			tagValuesMap.put(tv.getTagname(), tv);
		}
		return tagValuesMap;
	}	

	public void save(List<MappedFieldValue> fieldValues, Process process, String username) {
		tryAndInform("Error persting mapping field values to local database", () -> {
			logger.info("Persisting mapped field values");
			
			transactional(MySQL, (s) -> {
				for(int i = 0; i < fieldValues.size(); i++) {
					MappedFieldValue value = fieldValues.get(i);
					value.setProcess(process);
					value.setCreatedBy(username);
					value.setDateCreated(new Date());
					dao.save(value);
				}
				logger.info(String.format("%d field values persisted", fieldValues.size()));
				return null;
			});

			return null;
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

	public List<MappedFieldValue> cloneMappings(Process origin, Process destination) {
		BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);

		List<MappedFieldValue> mappings = getValuesForProcess(origin.getId());
		logger.info(String.format("Cloning %d mapped values for process %d", mappings.size(), origin.getId()));
		
		List<MappedFieldValue> result = Lists.newLinkedList(); 
		transactional(MySQL, (session) -> {
			for(MappedFieldValue mapping : mappings) {
				MappedFieldValue value = new MappedFieldValue();
				
				try {
					BeanUtils.copyProperties(value, mapping);
				} catch (Exception e) {
					// This should never happen, it's the same object type.
					logger.error(String.format("Error cloning field value id: %d", mapping.getId()), e);
				}
				
				value.setId(null);
				value.setProcess(destination);
				value.setCreatedBy(destination.getStartedBy());
				value.setDateCreated(new Date());
				dao.save(value);
				result.add(value);
			}
			return null;
		});
		logger.info(String.format("%d field values cloned", result.size()));
		return result;
	}
}
