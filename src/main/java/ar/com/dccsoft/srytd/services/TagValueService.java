package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.SQLSERVER;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.TagValueDao;
import ar.com.dccsoft.srytd.model.DeviceMapping;
import ar.com.dccsoft.srytd.model.TagValue;
import jersey.repackaged.com.google.common.collect.Sets;

public class TagValueService {

	private static Logger logger = LoggerFactory.getLogger(TagValueService.class);
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private ProcessAlertService processAlertService = new ProcessAlertService();
	private TagValueDao tagDao = new TagValueDao();

	public Set<TagValue> readOneHourValues(Date from, List<DeviceMapping> deviceMappings) {
		return tryAndInform("Error reading field values", () -> {
			return transactional(SQLSERVER, (session) -> {
				Set<TagValue> values = Sets.newHashSet();
				
				for (DeviceMapping device : deviceMappings) {
					Date adjustedFrom = DateUtils.addSeconds(from, device.getTimeOffset());
					
					logger.info(String.format(
							"Reading 1 hour values for device %s starting at %s", device.getName(), format.format(adjustedFrom)));
					
					appendValueOrAlert(values, device.getPresion(), adjustedFrom);
					appendValueOrAlert(values, device.getTemperatura(), adjustedFrom);
					appendValueOrAlert(values, device.getCaudal_horario(), adjustedFrom);
					appendValueOrAlert(values, device.getVolumen_bruto_acumulado(), adjustedFrom);
					appendValueOrAlert(values, device.getVolumen_neto_hoy(), adjustedFrom);
					appendValueOrAlert(values, device.getCaudal_horario_9300(), adjustedFrom);
					appendValueOrAlert(values, device.getVolumen_acumulado_9300(), adjustedFrom);
					appendValueOrAlert(values, device.getVolumen_desplazado(), adjustedFrom);
					appendValueOrAlert(values, device.getAltura_liquida(), adjustedFrom);
					appendValueOrAlert(values, device.getMf(), adjustedFrom);
					appendValueOrAlert(values, device.getCtl(), adjustedFrom);
					appendValueOrAlert(values, device.getCpl(), adjustedFrom);
					appendValueOrAlert(values, device.getFactor_k(), adjustedFrom);
					appendValueOrAlert(values, device.getPulsos_brutos(), adjustedFrom);
					appendValueOrAlert(values, device.getFcv(), adjustedFrom);
					appendValueOrAlert(values, device.getCtsh(), adjustedFrom);
					appendValueOrAlert(values, device.getPorcentaje_agua(), adjustedFrom);
					appendValueOrAlert(values, device.getPoder_calorifico(), adjustedFrom);
					appendValueOrAlert(values, device.getDensidad_relativa(), adjustedFrom);
					appendValueOrAlert(values, device.getCo2(), adjustedFrom);
					appendValueOrAlert(values, device.getN2(), adjustedFrom);
					appendValueOrAlert(values, device.getSh2(), adjustedFrom);
					appendValueOrAlert(values, device.getC1(), adjustedFrom);
					appendValueOrAlert(values, device.getC2(), adjustedFrom);
					appendValueOrAlert(values, device.getC3(), adjustedFrom);
					appendValueOrAlert(values, device.getIc4(), adjustedFrom);
					appendValueOrAlert(values, device.getNc4(), adjustedFrom);
					appendValueOrAlert(values, device.getIc5(), adjustedFrom);
					appendValueOrAlert(values, device.getNc5(), adjustedFrom);
					appendValueOrAlert(values, device.getC6(), adjustedFrom);
					appendValueOrAlert(values, device.getVolumen_seco(), adjustedFrom);
					appendValueOrAlert(values, device.getInicio_transac(), adjustedFrom);
					appendValueOrAlert(values, device.getFin_transac(), adjustedFrom);
					appendValueOrAlert(values, device.getVolumen_hoy_9300(), adjustedFrom);
					appendValueOrAlert(values, device.getDensidad(), adjustedFrom);
					appendValueOrAlert(values, device.getVolumen_bruto_hoy(), adjustedFrom);
					appendValueOrAlert(values, device.getVolumen_neto_hoy(), adjustedFrom);
					appendValueOrAlert(values, device.getPresion(), adjustedFrom);
				}
				return values;
			});
		});
		
	}

	public void appendValueOrAlert(Set<TagValue> values, String tag, Date from) {
		if(StringUtils.isNotBlank(tag)) {
			TagValue tagValue = readOneHourValues(from, tag);
			if(tagValue == null) {
				String msg = String.format("Values for tag %s not found.", tag);
				logger.warn(msg);
				processAlertService.addWarning("Tag without value", msg);
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
