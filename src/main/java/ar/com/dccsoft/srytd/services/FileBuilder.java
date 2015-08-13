package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import ar.com.dccsoft.srytd.model.FieldValue;
import ar.com.dccsoft.srytd.model.TagMapping;

import com.google.common.collect.Maps;

public class FileBuilder {

	// TODO . parameterize delimiter
	private CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',');
	private List<FieldValue> fieldValues;
	private Map<String, String> mappings;
	private AppPropertyService propService = new AppPropertyService();

	public FileBuilder withValues(List<FieldValue> fieldValues) {
		this.fieldValues = fieldValues;
		return this;
	}

	public FileBuilder withMappings(List<TagMapping> mappings) {
		this.mappings = asMap(mappings);
		return this;
	}

	public ByteArrayOutputStream build() {
		return tryAndInform("Error building file", () -> {
			format = formatWithHeaders();
			StringBuilder sb = new StringBuilder("");

			try (CSVPrinter printer = new CSVPrinter(sb, format); ByteArrayOutputStream os = new ByteArrayOutputStream();) {
				String ID_EMPRESA = propService.getCompanyId();
				String ID_INSTALACION = propService.getFacilityId();

				// Automatic readings
				for (FieldValue v : fieldValues) {
					String tagCode = mappings.get(v.getTag());
					Date ts = v.getTimestamp();
					String timestamp = String.format("%td-%tm-%tY %tH:%tM", ts, ts, ts, ts, ts);
					String readingType = "A";

					// TODO . Parameterize decimal separator
				printer.printRecord(ID_EMPRESA, ID_INSTALACION, tagCode, timestamp, readingType, v.getPresion(), v.getPresion_q(),
						v.getTemperatura(), v.getTemperatura_q(), v.getCaudal_horario(), v.getCaudal_horario_q(),
						v.getVolumen_bruto_acumulado(), v.getVolumen_bruto_acumulado_q(), v.getVolumen_neto_hoy(),
						v.getVolumen_neto_hoy_q(), v.getCaudal_horario_9300(), v.getCaudal_horario_9300_q(), v.getVolumen_acumulado_9300(),
						v.getVolumen_acumulado_9300_q(), v.getVolumen_desplazado(), v.getVolumen_desplazado_q(), v.getAltura_liquida(),
						v.getAltura_liquida_q(), v.getMf(), v.getMf_q(), v.getCtl(), v.getCtl_q(), v.getCpl(), v.getCpl_q(),
						v.getFactor_k(), v.getFactor_k_q(), v.getPulsos_brutos(), v.getPulsos_brutos_q(), v.getFcv(), v.getFcv_q(),
						v.getCtsh(), v.getCtsh_q(), v.getPorcentaje_agua(), v.getPorcentaje_agua_q(), v.getPoder_calorifico(),
						v.getPoder_calorifico_q(), v.getDensidad_relativa(), v.getDensidad_relativa_q(), v.getCo2(), v.getCo2_q(),
						v.getN2(), v.getN2_q(), v.getSh2(), v.getSh2_q(), v.getC1(), v.getC1_q(), v.getC2(), v.getC2_q(), v.getC3(),
						v.getC3_q(), v.getIc4(), v.getIc4_q(), v.getNc4(), v.getNc4_q(), v.getIc5(), v.getIc5_q(), v.getNc5(),
						v.getNc5_q(), v.getC6(), v.getC6_q(), v.getVolumen_seco(), v.getVolumen_seco_q(), v.getInicio_transac(),
						v.getInicio_transac_q(), v.getFin_transac(), v.getFin_transac_q(), v.getVolumen_hoy_9300(),
						v.getVolumen_hoy_9300_q(), v.getDensidad(), v.getDensidad_q(), v.getVolumen_bruto_hoy(),
						v.getVolumen_bruto_hoy_q(), v.getVolumen_neto_acumulado(), v.getVolumen_neto_acumulado_q());
			}

			// TODO . Manual values

			os.write(sb.toString().getBytes());

			return os;
		} catch (Exception e) {
			throw new RuntimeException("Error building CSV", e);
		}
	}	);
	}

	private CSVFormat formatWithHeaders() {
		return format.withHeader("idempresa", "idinstalacion", "tagmedidor", "timestamp", "manual_auto", "presion", "presion_q",
				"temperatura", "temperatura_q", "caudal_horario", "caudal_horario_q", "volumen_bruto_acumulado",
				"volumen_bruto_acumulado_q", "volumen_neto_hoy", "volumen_neto_hoy_q", "caudal_horario_9300", "caudal_horario_9300_q",
				"volumen_acumulado_9300", "volumen_acumulado_9300_q", "volumen_desplazado", "volumen_desplazado_q", "altura_liquida",
				"altura_liquida_q", "mf", "mf_q", "ctl", "ctl_q", "cpl", "cpl_q", "factor_k", "factor_k_q", "pulsos_brutos",
				"pulsos_brutos_q", "fcv", "fcv_q", "ctsh", "ctsh_q", "porcentaje_agua", "porcentaje_agua_q", "poder_calorifico",
				"poder_calorifico_q", "densidad_relativa", "densidad_relativa_q", "co2", "co2_q", "n2", "n2_q", "sh2", "sh2_q", "c1",
				"c1_q", "c2", "c2_q", "c3", "c3_q", "ic4", "ic4_q", "nc4", "nc4_q", "Ic5", "Ic5_q", "nc5", "nc5_q", "c6", "c6_q",
				"volumen_seco", "Volumen_seco_q", "inicio_transac", "inicio_transac_q", "fin_transac", "Fin_transac_q", "Volumen_hoy_9300",
				"Volumen_hoy_9300_q", "densidad", "Densidad_q", "Volumen_bruto_hoy", "Volumen_bruto_hoy_q", "Volumen_neto_acumulado",
				"Volumen_neto_acumulado_q");
	}

	private Map<String, String> asMap(List<TagMapping> mappings) {
		Map<String, String> result = Maps.newHashMap();
		for (TagMapping m : mappings) {
			result.put(m.getName(), m.getCode());
		}
		return result;
	}

}
