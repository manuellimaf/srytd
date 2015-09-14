package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.SQLSERVER;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jersey.repackaged.com.google.common.collect.Sets;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.TagValueDao;
import ar.com.dccsoft.srytd.model.DeviceMapping;
import ar.com.dccsoft.srytd.model.TagValue;

public class TagValueService {

	private static Logger logger = LoggerFactory.getLogger(TagValueService.class);
	private ProcessAlertService processAlertService = new ProcessAlertService();

	private TagValueDao tagDao = new TagValueDao();

	public Set<TagValue> readOneHourValues(Date from, List<DeviceMapping> deviceMappings) {
		return tryAndInform("Error reading field values", () -> {
			return transactional(SQLSERVER, (session) -> {
				Set<TagValue> values = Sets.newHashSet();
				
				for (DeviceMapping device : deviceMappings) {
					logger.info(String.format("Reading values for device %s", device.getName()));
					
					appendValueOrAlert(values, device.getPresion(), from);
					appendValueOrAlert(values, device.getTemperatura(), from);
					appendValueOrAlert(values, device.getCaudal_horario(), from);
					appendValueOrAlert(values, device.getVolumen_bruto_acumulado(), from);
					appendValueOrAlert(values, device.getVolumen_neto_hoy(), from);
					appendValueOrAlert(values, device.getCaudal_horario_9300(), from);
					appendValueOrAlert(values, device.getVolumen_acumulado_9300(), from);
					appendValueOrAlert(values, device.getVolumen_desplazado(), from);
					appendValueOrAlert(values, device.getAltura_liquida(), from);
					appendValueOrAlert(values, device.getMf(), from);
					appendValueOrAlert(values, device.getCtl(), from);
					appendValueOrAlert(values, device.getCpl(), from);
					appendValueOrAlert(values, device.getFactor_k(), from);
					appendValueOrAlert(values, device.getPulsos_brutos(), from);
					appendValueOrAlert(values, device.getFcv(), from);
					appendValueOrAlert(values, device.getCtsh(), from);
					appendValueOrAlert(values, device.getPorcentaje_agua(), from);
					appendValueOrAlert(values, device.getPoder_calorifico(), from);
					appendValueOrAlert(values, device.getDensidad_relativa(), from);
					appendValueOrAlert(values, device.getCo2(), from);
					appendValueOrAlert(values, device.getN2(), from);
					appendValueOrAlert(values, device.getSh2(), from);
					appendValueOrAlert(values, device.getC1(), from);
					appendValueOrAlert(values, device.getC2(), from);
					appendValueOrAlert(values, device.getC3(), from);
					appendValueOrAlert(values, device.getIc4(), from);
					appendValueOrAlert(values, device.getNc4(), from);
					appendValueOrAlert(values, device.getIc5(), from);
					appendValueOrAlert(values, device.getNc5(), from);
					appendValueOrAlert(values, device.getC6(), from);
					appendValueOrAlert(values, device.getVolumen_seco(), from);
					appendValueOrAlert(values, device.getInicio_transac(), from);
					appendValueOrAlert(values, device.getFin_transac(), from);
					appendValueOrAlert(values, device.getVolumen_hoy_9300(), from);
					appendValueOrAlert(values, device.getDensidad(), from);
					appendValueOrAlert(values, device.getVolumen_bruto_hoy(), from);
					appendValueOrAlert(values, device.getVolumen_neto_hoy(), from);
					appendValueOrAlert(values, device.getPresion(), from);
				}
				return values;
			});
		});
		
	}

	public void appendValueOrAlert(Set<TagValue> values, String tag, Date from) {
		if(StringUtils.isNotBlank(tag)) {
			TagValue tagValue = readOneHourValues(from, tag);
			if(tagValue == null) {
				logger.warn("Values for tag " + tag + " not found.");
				processAlertService.addWarning("Tag without value", 
						String.format("Could not find value for tag %s", tag));
			} else {
				values.add(tagValue);
			}
		}
	}

	public TagValue readOneHourValues(Date from, String tag) {
		return tryAndInform("Error reading tag value", () -> {
			return transactional(SQLSERVER, (session) -> {
				Date to = DateUtils.addHours(from, 1);
				TagValue tagValue = tagDao.readTagValue(from, to, tag);
				return tagValue;
			});
		});
	}
}
