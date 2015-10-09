package ar.com.dccsoft.srytd.services.process;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.model.MappedFieldValue;
import ar.com.dccsoft.srytd.services.AppPropertyService;
import ar.com.dccsoft.srytd.utils.Config;

public class FileBuilder {

	private static Logger logger = LoggerFactory.getLogger(FileBuilder.class);
	private CSVFormat format = CSVFormat.DEFAULT.withDelimiter(Config.getDelimiter());
	private NumberFormat nf = numberFormat();
	private List<MappedFieldValue> mappings;
	private AppPropertyService propService = new AppPropertyService();

	private String Q_OK = "ok";
	private String Q_ERROR = "ec";
	
	public FileBuilder withMappings(List<MappedFieldValue> mappings) {
		this.mappings = mappings;
		return this;
	}

	public FileBuildResult build() {
		logger.info(String.format("Starting to create file from %d values", mappings.size()));
		return tryAndInform("Error building file", () -> {
			FileBuildResult result = new FileBuildResult();
			format = formatWithHeaders();
			
			StringBuilder sb = new StringBuilder("");

			try (CSVPrinter printer = new CSVPrinter(sb, format); ByteArrayOutputStream os = new ByteArrayOutputStream();) {
				String ID_EMPRESA = propService.getCompanyId();

				for (MappedFieldValue v : mappings) {
					String code = v.getCode();

					Date ts = v.getTimestamp();
					String timestamp = String.format("%td-%tm-%tY %tH:%tM", ts, ts, ts, ts, ts);
					String readingType = v.getValueType();

					// TODO - Ojo con los campos inicio/fin transac!! (no se si son números o fechas)
					printer.printRecord(
								ID_EMPRESA, 
								v.getDeviceId(), 
								code, 
								timestamp, 
								readingType, 
								numFormat(v.getPresion()), qFormat(v.getPresion_q()), 
								numFormat(v.getTemperatura()), qFormat(v.getTemperatura_q()), 
								numFormat(v.getCaudal_horario()), qFormat(v.getCaudal_horario_q()), 
								numFormat(v.getVolumen_bruto_acumulado()), qFormat(v.getVolumen_bruto_acumulado_q()),
								numFormat(v.getVolumen_neto_hoy()), qFormat(v.getVolumen_neto_hoy_q()), 
								numFormat(v.getCaudal_horario_9300()), qFormat(v.getCaudal_horario_9300_q()), 
								numFormat(v.getVolumen_acumulado_9300()), qFormat(v.getVolumen_acumulado_9300_q()),
								numFormat(v.getVolumen_desplazado()), qFormat(v.getVolumen_desplazado_q()), 
								numFormat(v.getAltura_liquida()), qFormat(v.getAltura_liquida_q()), 
								numFormat(v.getMf()), qFormat(v.getMf_q()), 
								numFormat(v.getCtl()), qFormat(v.getCtl_q()),
								numFormat(v.getCpl()), qFormat(v.getCpl_q()), 
								numFormat(v.getFactor_k()), qFormat(v.getFactor_k_q()),
								numFormat(v.getPulsos_brutos()), qFormat(v.getPulsos_brutos_q()), 
								numFormat(v.getFcv()), qFormat(v.getFcv_q()),
								numFormat(v.getCtsh()), qFormat(v.getCtsh_q()), 
								numFormat(v.getPorcentaje_agua()), qFormat(v.getPorcentaje_agua_q()),
								numFormat(v.getPoder_calorifico()), qFormat(v.getPoder_calorifico_q()), 
								numFormat(v.getDensidad_relativa()), qFormat(v.getDensidad_relativa_q()), 
								numFormat(v.getCo2()), qFormat(v.getCo2_q()), 
								numFormat(v.getN2()), qFormat(v.getN2_q()),
								numFormat(v.getSh2()), qFormat(v.getSh2_q()), 
								numFormat(v.getC1()), qFormat(v.getC1_q()), 
								numFormat(v.getC2()), qFormat(v.getC2_q()),
								numFormat(v.getC3()), qFormat(v.getC3_q()), 
								numFormat(v.getIc5()), qFormat(v.getIc5_q()), 
								numFormat(v.getIc4()), qFormat(v.getIc4_q()), 
								numFormat(v.getNc4()), qFormat(v.getNc4_q()),
								numFormat(v.getNc5()), qFormat(v.getNc5_q()), 
								numFormat(v.getC6()), qFormat(v.getC6_q()),
								numFormat(v.getVolumen_seco()), qFormat(v.getVolumen_seco_q()), 
								numFormat(v.getInicio_transac()), qFormat(v.getInicio_transac_q()),
								numFormat(v.getFin_transac()), qFormat(v.getFin_transac_q()), 
								numFormat(v.getVolumen_hoy_9300()), qFormat(v.getVolumen_hoy_9300_q()),
								numFormat(v.getVolumen_neto_acumulado()), qFormat(v.getVolumen_neto_acumulado_q()),
								numFormat(v.getDensidad()), qFormat(v.getDensidad_q()), 
								numFormat(v.getVolumen_bruto_hoy()), qFormat(v.getVolumen_bruto_hoy_q()));

					result.addProcessedValue();
				}

				result.setFile(sb.toString());
				logger.info(String.format("File built (%d values total, %d values with missing mappings)", result.getProcessedValues(),
						result.getUnprocessedValues()));
				return result;
			} catch (Exception e) {
				throw new RuntimeException("Error building CSV", e);
			}
		});
	}

	private String numFormat(BigDecimal value) {
		if(value == null)
			return "";
		return nf.format(value);
	}
	
	private CSVFormat formatWithHeaders() {
		return format.withHeader(
				"idempresa", 
				"idinstalacion", 
				"tagmedidor", 
				"timestamp", 
				"manual_auto", 
				"presion", "presion_q",
				"temperatura", "temperatura_q", 
				"caudal_horario", "caudal_horario_q", 
				"volumen_bruto_acumulado",
				"volumen_bruto_acumulado_q", 
				"volumen_neto_hoy", "volumen_neto_hoy_q", 
				"caudal_horario_9300", "caudal_horario_9300_q",
				"volumen_acumulado_9300", "volumen_acumulado_9300_q", 
				"volumen_desplazado", "volumen_desplazado_q", 
				"altura_liquida", "altura_liquida_q", 
				"mf", "mf_q", 
				"ctl", "ctl_q", 
				"cpl", "cpl_q", 
				"factor_k", "factor_k_q", 
				"pulsos_brutos", "pulsos_brutos_q", 
				"fcv", "fcv_q", 
				"ctsh", "ctsh_q", 
				"porcentaje_agua", "porcentaje_agua_q", 
				"poder_calorifico", "poder_calorifico_q", 
				"densidad_relativa", "densidad_relativa_q", 
				"co2", "co2_q", 
				"n2", "n2_q", 
				"sh2", "sh2_q", 
				"c1", "c1_q", 
				"c2", "c2_q", 
				"c3", "c3_q", 
				"ic5", "ic5_q", 
				"ic4", "ic4_q", 
				"nc4", "nc4_q", 
				"nc5", "nc5_q", 
				"c6", "c6_q",
				"volumen_seco", "Volumen_seco_q", 
				"inicio_transac", "inicio_transac_q", 
				"fin_transac", "Fin_transac_q", 
				"Volumen_hoy_9300", "Volumen_hoy_9300_q", 
				"Volumen_neto_acumulado", "Volumen_neto_acumulado_q",
				"densidad", "Densidad_q", 
				"Volumen_bruto_hoy", "Volumen_bruto_hoy_q");
	}

	private String qFormat(String value) {
		if (value == null)
			return "";
		return "0".equals(value)? Q_OK: Q_ERROR;
	}
	
	private NumberFormat numberFormat() {
		DecimalFormatSymbols syms = DecimalFormatSymbols.getInstance();
		syms.setDecimalSeparator(Config.getDecimalSeparator());

		//No formateaba bien valores muy bajos
		//DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
		//format.setMaximumFractionDigits(340);
		
		DecimalFormat format = new DecimalFormat(Config.getDecimalPattern());
		format.setDecimalFormatSymbols(syms);
		format.setGroupingUsed(false);

		return format;
	}

	public static class FileBuildResult {
		private String file;
		private Long processedValues = 0L;
		private Long unprocessedValues = 0L;
		private List<String> missingMappings = Lists.newLinkedList();

		public String getFile() {
			return file;
		}

		public void setFile(String file) {
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
