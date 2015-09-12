package ar.com.dccsoft.srytd.api.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ManualValuesDTO {
	private Long id;
	private String code;
	private String deviceId;
	private Date timestamp;
	private String valueDate;
	private String valueTime;
	
	private String valueType;

	private BigDecimal presion;
	private BigDecimal temperatura;
	private BigDecimal caudal_horario;
	private BigDecimal volumen_bruto_acumulado;
	private BigDecimal volumen_neto_hoy;
	private BigDecimal caudal_horario_9300;
	private BigDecimal volumen_acumulado_9300;
	private BigDecimal volumen_desplazado;
	private BigDecimal altura_liquida;
	private BigDecimal mf;
	private BigDecimal ctl;
	private BigDecimal cpl;
	private BigDecimal factor_k;
	private BigDecimal pulsos_brutos;
	private BigDecimal fcv;
	private BigDecimal ctsh;
	private BigDecimal porcentaje_agua;
	private BigDecimal poder_calorifico;
	private BigDecimal densidad_relativa;
	private BigDecimal co2;
	private BigDecimal n2;
	private BigDecimal sh2;
	private BigDecimal c1;
	private BigDecimal c2;
	private BigDecimal c3;
	private BigDecimal ic4;
	private BigDecimal nc4;
	private BigDecimal ic5;
	private BigDecimal nc5;
	private BigDecimal c6;
	private BigDecimal volumen_seco;
	private BigDecimal inicio_transac;
	private BigDecimal fin_transac;
	private BigDecimal volumen_hoy_9300;
	private BigDecimal densidad;
	private BigDecimal volumen_bruto_hoy;
	private BigDecimal volumen_neto_acumulado;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public BigDecimal getPresion() {
		return presion;
	}

	public void setPresion(BigDecimal presion) {
		this.presion = presion;
	}

	public BigDecimal getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(BigDecimal temperatura) {
		this.temperatura = temperatura;
	}

	public BigDecimal getCaudal_horario() {
		return caudal_horario;
	}

	public void setCaudal_horario(BigDecimal caudal_horario) {
		this.caudal_horario = caudal_horario;
	}

	public BigDecimal getVolumen_bruto_acumulado() {
		return volumen_bruto_acumulado;
	}

	public void setVolumen_bruto_acumulado(BigDecimal volumen_bruto_acumulado) {
		this.volumen_bruto_acumulado = volumen_bruto_acumulado;
	}

	public BigDecimal getVolumen_neto_hoy() {
		return volumen_neto_hoy;
	}

	public void setVolumen_neto_hoy(BigDecimal volumen_neto_hoy) {
		this.volumen_neto_hoy = volumen_neto_hoy;
	}

	public BigDecimal getCaudal_horario_9300() {
		return caudal_horario_9300;
	}

	public void setCaudal_horario_9300(BigDecimal caudal_horario_9300) {
		this.caudal_horario_9300 = caudal_horario_9300;
	}

	public BigDecimal getVolumen_acumulado_9300() {
		return volumen_acumulado_9300;
	}

	public void setVolumen_acumulado_9300(BigDecimal volumen_acumulado_9300) {
		this.volumen_acumulado_9300 = volumen_acumulado_9300;
	}

	public BigDecimal getVolumen_desplazado() {
		return volumen_desplazado;
	}

	public void setVolumen_desplazado(BigDecimal volumen_desplazado) {
		this.volumen_desplazado = volumen_desplazado;
	}

	public BigDecimal getAltura_liquida() {
		return altura_liquida;
	}

	public void setAltura_liquida(BigDecimal altura_liquida) {
		this.altura_liquida = altura_liquida;
	}

	public BigDecimal getMf() {
		return mf;
	}

	public void setMf(BigDecimal mf) {
		this.mf = mf;
	}

	public BigDecimal getCtl() {
		return ctl;
	}

	public void setCtl(BigDecimal ctl) {
		this.ctl = ctl;
	}

	public BigDecimal getCpl() {
		return cpl;
	}

	public void setCpl(BigDecimal cpl) {
		this.cpl = cpl;
	}

	public BigDecimal getFactor_k() {
		return factor_k;
	}

	public void setFactor_k(BigDecimal factor_k) {
		this.factor_k = factor_k;
	}

	public BigDecimal getPulsos_brutos() {
		return pulsos_brutos;
	}

	public void setPulsos_brutos(BigDecimal pulsos_brutos) {
		this.pulsos_brutos = pulsos_brutos;
	}

	public BigDecimal getFcv() {
		return fcv;
	}

	public void setFcv(BigDecimal fcv) {
		this.fcv = fcv;
	}

	public BigDecimal getCtsh() {
		return ctsh;
	}

	public void setCtsh(BigDecimal ctsh) {
		this.ctsh = ctsh;
	}

	public BigDecimal getPorcentaje_agua() {
		return porcentaje_agua;
	}

	public void setPorcentaje_agua(BigDecimal porcentaje_agua) {
		this.porcentaje_agua = porcentaje_agua;
	}

	public BigDecimal getPoder_calorifico() {
		return poder_calorifico;
	}

	public void setPoder_calorifico(BigDecimal poder_calorifico) {
		this.poder_calorifico = poder_calorifico;
	}

	public BigDecimal getDensidad_relativa() {
		return densidad_relativa;
	}

	public void setDensidad_relativa(BigDecimal densidad_relativa) {
		this.densidad_relativa = densidad_relativa;
	}

	public BigDecimal getCo2() {
		return co2;
	}

	public void setCo2(BigDecimal co2) {
		this.co2 = co2;
	}

	public BigDecimal getN2() {
		return n2;
	}

	public void setN2(BigDecimal n2) {
		this.n2 = n2;
	}

	public BigDecimal getSh2() {
		return sh2;
	}

	public void setSh2(BigDecimal sh2) {
		this.sh2 = sh2;
	}

	public BigDecimal getC1() {
		return c1;
	}

	public void setC1(BigDecimal c1) {
		this.c1 = c1;
	}

	public BigDecimal getC2() {
		return c2;
	}

	public void setC2(BigDecimal c2) {
		this.c2 = c2;
	}

	public BigDecimal getC3() {
		return c3;
	}

	public void setC3(BigDecimal c3) {
		this.c3 = c3;
	}

	public BigDecimal getIc4() {
		return ic4;
	}

	public void setIc4(BigDecimal ic4) {
		this.ic4 = ic4;
	}

	public BigDecimal getNc4() {
		return nc4;
	}

	public void setNc4(BigDecimal nc4) {
		this.nc4 = nc4;
	}

	public BigDecimal getIc5() {
		return ic5;
	}

	public void setIc5(BigDecimal ic5) {
		this.ic5 = ic5;
	}

	public BigDecimal getNc5() {
		return nc5;
	}

	public void setNc5(BigDecimal nc5) {
		this.nc5 = nc5;
	}

	public BigDecimal getC6() {
		return c6;
	}

	public void setC6(BigDecimal c6) {
		this.c6 = c6;
	}

	public BigDecimal getVolumen_seco() {
		return volumen_seco;
	}

	public void setVolumen_seco(BigDecimal volumen_seco) {
		this.volumen_seco = volumen_seco;
	}

	public BigDecimal getVolumen_hoy_9300() {
		return volumen_hoy_9300;
	}

	public void setVolumen_hoy_9300(BigDecimal volumen_hoy_9300) {
		this.volumen_hoy_9300 = volumen_hoy_9300;
	}

	public BigDecimal getDensidad() {
		return densidad;
	}

	public void setDensidad(BigDecimal densidad) {
		this.densidad = densidad;
	}

	public BigDecimal getVolumen_bruto_hoy() {
		return volumen_bruto_hoy;
	}

	public void setVolumen_bruto_hoy(BigDecimal volumen_bruto_hoy) {
		this.volumen_bruto_hoy = volumen_bruto_hoy;
	}

	public BigDecimal getVolumen_neto_acumulado() {
		return volumen_neto_acumulado;
	}

	public void setVolumen_neto_acumulado(BigDecimal volumen_neto_acumulado) {
		this.volumen_neto_acumulado = volumen_neto_acumulado;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getValueTime() {
		return valueTime;
	}

	public void setValueTime(String valueTime) {
		this.valueTime = valueTime;
	}

	public BigDecimal getInicio_transac() {
		return inicio_transac;
	}

	public void setInicio_transac(BigDecimal inicio_transac) {
		this.inicio_transac = inicio_transac;
	}

	public BigDecimal getFin_transac() {
		return fin_transac;
	}

	public void setFin_transac(BigDecimal fin_transac) {
		this.fin_transac = fin_transac;
	}

}
