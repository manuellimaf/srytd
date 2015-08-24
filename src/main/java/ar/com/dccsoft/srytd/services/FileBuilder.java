package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jersey.repackaged.com.google.common.collect.Lists;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.IOUtils;

import ar.com.dccsoft.srytd.model.Device;
import ar.com.dccsoft.srytd.model.FieldValue;
import ar.com.dccsoft.srytd.utils.Config;

import com.google.common.collect.Maps;

public class FileBuilder {

	private CSVFormat format = CSVFormat.DEFAULT.withDelimiter(Config.getDelimiter());
	private List<FieldValue> fieldValues;
	private Map<String, String> mappings;
	private AppPropertyService propService = new AppPropertyService();

	public FileBuilder withValues(List<FieldValue> fieldValues) {
		this.fieldValues = fieldValues;
		return this;
	}

	public FileBuilder withMappings(List<Device> mappings) {
		this.mappings = asMap(mappings);
		return this;
	}

	public FileBuildResult build() {
		return tryAndInform("Error building file", () -> {
			FileBuildResult result = new FileBuildResult();
			format = formatWithHeaders();
			NumberFormat nf = numberFormat();
			StringBuilder sb = new StringBuilder("");

			try (CSVPrinter printer = new CSVPrinter(sb, format); ByteArrayOutputStream os = new ByteArrayOutputStream();) {
				String ID_EMPRESA = propService.getCompanyId();

				// Automatic readings
				for (FieldValue v : fieldValues) {
					String tagCode = mappings.get(v.getDeviceId());

					if (tagCode != null) {
						Date ts = v.getTimestamp();
						String timestamp = String.format("%td-%tm-%tY %tH:%tM", ts, ts, ts, ts, ts);
						String readingType = "A";

						printer.printRecord(ID_EMPRESA, v.getDeviceId(), tagCode, timestamp, readingType, nf.format(v.getPresion()),
								v.getPresion_q(), nf.format(v.getTemperatura()), v.getTemperatura_q(), nf.format(v.getCaudal_horario()),
								v.getCaudal_horario_q(), nf.format(v.getVolumen_bruto_acumulado()), v.getVolumen_bruto_acumulado_q(),
								nf.format(v.getVolumen_neto_hoy()), v.getVolumen_neto_hoy_q(), nf.format(v.getCaudal_horario_9300()),
								v.getCaudal_horario_9300_q(), nf.format(v.getVolumen_acumulado_9300()), v.getVolumen_acumulado_9300_q(),
								nf.format(v.getVolumen_desplazado()), v.getVolumen_desplazado_q(), nf.format(v.getAltura_liquida()),
								v.getAltura_liquida_q(), nf.format(v.getMf()), v.getMf_q(), nf.format(v.getCtl()), v.getCtl_q(),
								nf.format(v.getCpl()), v.getCpl_q(), nf.format(v.getFactor_k()), v.getFactor_k_q(),
								nf.format(v.getPulsos_brutos()), v.getPulsos_brutos_q(), nf.format(v.getFcv()), v.getFcv_q(),
								nf.format(v.getCtsh()), v.getCtsh_q(), nf.format(v.getPorcentaje_agua()), v.getPorcentaje_agua_q(),
								nf.format(v.getPoder_calorifico()), v.getPoder_calorifico_q(), nf.format(v.getDensidad_relativa()),
								v.getDensidad_relativa_q(), nf.format(v.getCo2()), v.getCo2_q(), nf.format(v.getN2()), v.getN2_q(),
								nf.format(v.getSh2()), v.getSh2_q(), nf.format(v.getC1()), v.getC1_q(), nf.format(v.getC2()), v.getC2_q(),
								nf.format(v.getC3()), v.getC3_q(), nf.format(v.getIc4()), v.getIc4_q(), nf.format(v.getNc4()),
								v.getNc4_q(), nf.format(v.getIc5()), v.getIc5_q(), nf.format(v.getNc5()), v.getNc5_q(),
								nf.format(v.getC6()), v.getC6_q(), nf.format(v.getVolumen_seco()), v.getVolumen_seco_q(),
								nf.format(v.getInicio_transac()), v.getInicio_transac_q(), nf.format(v.getFin_transac()),
								v.getFin_transac_q(), nf.format(v.getVolumen_hoy_9300()), v.getVolumen_hoy_9300_q(),
								nf.format(v.getDensidad()), v.getDensidad_q(), nf.format(v.getVolumen_bruto_hoy()),
								v.getVolumen_bruto_hoy_q(), nf.format(v.getVolumen_neto_acumulado()), v.getVolumen_neto_acumulado_q());

						result.addProcessedValue();
					} else {
						result.addMissingMapping(v.getDeviceId());
					}
				}

				// TODO . Manual values

				result.setFile(IOUtils.toInputStream(sb.toString()));
				return result;
			} catch (Exception e) {
				throw new RuntimeException("Error building CSV", e);
			}
		});
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

	private Map<String, String> asMap(List<Device> mappings) {
		Map<String, String> result = Maps.newHashMap();
		for (Device m : mappings) {
			result.put(m.getName(), m.getTag());
		}
		return result;
	}

	private NumberFormat numberFormat() {
		DecimalFormatSymbols syms = DecimalFormatSymbols.getInstance();
		syms.setDecimalSeparator(Config.getDecimalSeparator());
		DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
		format.setDecimalFormatSymbols(syms);
		format.setGroupingUsed(false);
		format.setMaximumFractionDigits(340);

		return format;
	}

	public static class FileBuildResult {
		private InputStream file;
		private Long processedValues = 0L;
		private Long unprocessedValues = 0L;
		private List<String> missingMappings = Lists.newLinkedList();

		public InputStream getFile() {
			return file;
		}

		public void setFile(InputStream file) {
			this.file = file;
		}

		public Long getProcessedValues() {
			return processedValues;
		}

		public void addProcessedValue() {
			this.processedValues++;
		}

		public Long getUnprocessedValues() {
			return unprocessedValues;
		}

		public void addMissingMapping(String name) {
			this.missingMappings.add(name);
			this.unprocessedValues++;
		}

	}
}
