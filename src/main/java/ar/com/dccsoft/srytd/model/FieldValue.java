package ar.com.dccsoft.srytd.model;

import java.util.Date;

public class FieldValue {

	private Long id;
	private String tag;
	private Date timestamp;
	private String valueType;
	
	private Double presion;
	private String presion_q;
	private Double temperatura;
	private String temperatura_q;
	private Double caudal_horario;
	private String caudal_horario_q;
	private Double volumen_bruto_acumulado;
	private String volumen_bruto_acumulado_q;
	private Double volumen_neto_hoy;
	private String volumen_neto_hoy_q;
	private Double caudal_horario_9300;
	private String caudal_horario_9300_q;
	private Double volumen_acumulado_9300;
	private String volumen_acumulado_9300_q;
	private Double volumen_desplazado;
	private String volumen_desplazado_q;
	private Double altura_liquida;
	private String altura_liquida_q;
	private Double mf;
	private String mf_q;
	private Double ctl;
	private String ctl_q;
	private Double cpl;
	private String cpl_q;
	private Double factor_k;
	private String factor_k_q;
	private Double pulsos_brutos;
	private String pulsos_brutos_q;
	private Double fcv;
	private String fcv_q;
	private Double ctsh;
	private String ctsh_q;
	private Double porcentaje_agua;
	private String porcentaje_agua_q;
	private Double poder_calorifico;
	private String poder_calorifico_q;
	private Double densidad_relativa;
	private String densidad_relativa_q;
	private Double co2;
	private String co2_q;
	private Double n2;
	private String n2_q;
	private Double sh2;
	private String sh2_q;
	private Double c1;
	private String c1_q;
	private Double c2;
	private String c2_q;
	private Double c3;
	private String c3_q;
	private Double ic4;
	private String ic4_q;
	private Double nc4;
	private String nc4_q;
	private Double ic5;
	private String ic5_q;
	private Double nc5;
	private String nc5_q;
	private Double c6;
	private String c6_q;
	private Double volumen_seco;
	private String volumen_seco_q;
	private Double inicio_transac;
	private String inicio_transac_q;
	private Double fin_transac;
	private String fin_transac_q;
	private Double volumen_hoy_9300;
	private String volumen_hoy_9300_q;
	private Double densidad;
	private String densidad_q;
	private Double volumen_bruto_hoy;
	private String volumen_bruto_hoy_q;
	private Double volumen_neto_acumulado;
	private String volumen_neto_acumulado_q;

	
	public Double getPresion() {
		return presion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setPresion(Double presion) {
		this.presion = presion;
	}
	public String getPresion_q() {
		return presion_q;
	}
	public void setPresion_q(String presion_q) {
		this.presion_q = presion_q;
	}
	public Double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}
	public String getTemperatura_q() {
		return temperatura_q;
	}
	public void setTemperatura_q(String temperatura_q) {
		this.temperatura_q = temperatura_q;
	}
	public Double getCaudal_horario() {
		return caudal_horario;
	}
	public void setCaudal_horario(Double caudal_horario) {
		this.caudal_horario = caudal_horario;
	}
	public String getCaudal_horario_q() {
		return caudal_horario_q;
	}
	public void setCaudal_horario_q(String caudal_horario_q) {
		this.caudal_horario_q = caudal_horario_q;
	}
	public Double getVolumen_bruto_acumulado() {
		return volumen_bruto_acumulado;
	}
	public void setVolumen_bruto_acumulado(Double volumen_bruto_acumulado) {
		this.volumen_bruto_acumulado = volumen_bruto_acumulado;
	}
	public String getVolumen_bruto_acumulado_q() {
		return volumen_bruto_acumulado_q;
	}
	public void setVolumen_bruto_acumulado_q(String volumen_bruto_acumulado_q) {
		this.volumen_bruto_acumulado_q = volumen_bruto_acumulado_q;
	}
	public Double getVolumen_neto_hoy() {
		return volumen_neto_hoy;
	}
	public void setVolumen_neto_hoy(Double volumen_neto_hoy) {
		this.volumen_neto_hoy = volumen_neto_hoy;
	}
	public String getVolumen_neto_hoy_q() {
		return volumen_neto_hoy_q;
	}
	public void setVolumen_neto_hoy_q(String volumen_neto_hoy_q) {
		this.volumen_neto_hoy_q = volumen_neto_hoy_q;
	}
	public Double getCaudal_horario_9300() {
		return caudal_horario_9300;
	}
	public void setCaudal_horario_9300(Double caudal_horario_9300) {
		this.caudal_horario_9300 = caudal_horario_9300;
	}
	public String getCaudal_horario_9300_q() {
		return caudal_horario_9300_q;
	}
	public void setCaudal_horario_9300_q(String caudal_horario_9300_q) {
		this.caudal_horario_9300_q = caudal_horario_9300_q;
	}
	public Double getVolumen_acumulado_9300() {
		return volumen_acumulado_9300;
	}
	public void setVolumen_acumulado_9300(Double volumen_acumulado_9300) {
		this.volumen_acumulado_9300 = volumen_acumulado_9300;
	}
	public String getVolumen_acumulado_9300_q() {
		return volumen_acumulado_9300_q;
	}
	public void setVolumen_acumulado_9300_q(String volumen_acumulado_9300_q) {
		this.volumen_acumulado_9300_q = volumen_acumulado_9300_q;
	}
	public Double getVolumen_desplazado() {
		return volumen_desplazado;
	}
	public void setVolumen_desplazado(Double volumen_desplazado) {
		this.volumen_desplazado = volumen_desplazado;
	}
	public String getVolumen_desplazado_q() {
		return volumen_desplazado_q;
	}
	public void setVolumen_desplazado_q(String volumen_desplazado_q) {
		this.volumen_desplazado_q = volumen_desplazado_q;
	}
	public Double getAltura_liquida() {
		return altura_liquida;
	}
	public void setAltura_liquida(Double altura_liquida) {
		this.altura_liquida = altura_liquida;
	}
	public String getAltura_liquida_q() {
		return altura_liquida_q;
	}
	public void setAltura_liquida_q(String altura_liquida_q) {
		this.altura_liquida_q = altura_liquida_q;
	}
	public Double getMf() {
		return mf;
	}
	public void setMf(Double mf) {
		this.mf = mf;
	}
	public String getMf_q() {
		return mf_q;
	}
	public void setMf_q(String mf_q) {
		this.mf_q = mf_q;
	}
	public Double getCtl() {
		return ctl;
	}
	public void setCtl(Double ctl) {
		this.ctl = ctl;
	}
	public String getCtl_q() {
		return ctl_q;
	}
	public void setCtl_q(String ctl_q) {
		this.ctl_q = ctl_q;
	}
	public Double getCpl() {
		return cpl;
	}
	public void setCpl(Double cpl) {
		this.cpl = cpl;
	}
	public String getCpl_q() {
		return cpl_q;
	}
	public void setCpl_q(String cpl_q) {
		this.cpl_q = cpl_q;
	}
	public Double getFactor_k() {
		return factor_k;
	}
	public void setFactor_k(Double factor_k) {
		this.factor_k = factor_k;
	}
	public String getFactor_k_q() {
		return factor_k_q;
	}
	public void setFactor_k_q(String factor_k_q) {
		this.factor_k_q = factor_k_q;
	}
	public Double getPulsos_brutos() {
		return pulsos_brutos;
	}
	public void setPulsos_brutos(Double pulsos_brutos) {
		this.pulsos_brutos = pulsos_brutos;
	}
	public String getPulsos_brutos_q() {
		return pulsos_brutos_q;
	}
	public void setPulsos_brutos_q(String pulsos_brutos_q) {
		this.pulsos_brutos_q = pulsos_brutos_q;
	}
	public Double getFcv() {
		return fcv;
	}
	public void setFcv(Double fcv) {
		this.fcv = fcv;
	}
	public String getFcv_q() {
		return fcv_q;
	}
	public void setFcv_q(String fcv_q) {
		this.fcv_q = fcv_q;
	}
	public Double getCtsh() {
		return ctsh;
	}
	public void setCtsh(Double ctsh) {
		this.ctsh = ctsh;
	}
	public String getCtsh_q() {
		return ctsh_q;
	}
	public void setCtsh_q(String ctsh_q) {
		this.ctsh_q = ctsh_q;
	}
	public Double getPorcentaje_agua() {
		return porcentaje_agua;
	}
	public void setPorcentaje_agua(Double porcentaje_agua) {
		this.porcentaje_agua = porcentaje_agua;
	}
	public String getPorcentaje_agua_q() {
		return porcentaje_agua_q;
	}
	public void setPorcentaje_agua_q(String porcentaje_agua_q) {
		this.porcentaje_agua_q = porcentaje_agua_q;
	}
	public Double getPoder_calorifico() {
		return poder_calorifico;
	}
	public void setPoder_calorifico(Double poder_calorifico) {
		this.poder_calorifico = poder_calorifico;
	}
	public String getPoder_calorifico_q() {
		return poder_calorifico_q;
	}
	public void setPoder_calorifico_q(String poder_calorifico_q) {
		this.poder_calorifico_q = poder_calorifico_q;
	}
	public Double getDensidad_relativa() {
		return densidad_relativa;
	}
	public void setDensidad_relativa(Double densidad_relativa) {
		this.densidad_relativa = densidad_relativa;
	}
	public String getDensidad_relativa_q() {
		return densidad_relativa_q;
	}
	public void setDensidad_relativa_q(String densidad_relativa_q) {
		this.densidad_relativa_q = densidad_relativa_q;
	}
	public Double getCo2() {
		return co2;
	}
	public void setCo2(Double co2) {
		this.co2 = co2;
	}
	public String getCo2_q() {
		return co2_q;
	}
	public void setCo2_q(String co2_q) {
		this.co2_q = co2_q;
	}
	public Double getN2() {
		return n2;
	}
	public void setN2(Double n2) {
		this.n2 = n2;
	}
	public String getN2_q() {
		return n2_q;
	}
	public void setN2_q(String n2_q) {
		this.n2_q = n2_q;
	}
	public Double getSh2() {
		return sh2;
	}
	public void setSh2(Double sh2) {
		this.sh2 = sh2;
	}
	public String getSh2_q() {
		return sh2_q;
	}
	public void setSh2_q(String sh2_q) {
		this.sh2_q = sh2_q;
	}
	public Double getC1() {
		return c1;
	}
	public void setC1(Double c1) {
		this.c1 = c1;
	}
	public String getC1_q() {
		return c1_q;
	}
	public void setC1_q(String c1_q) {
		this.c1_q = c1_q;
	}
	public Double getC2() {
		return c2;
	}
	public void setC2(Double c2) {
		this.c2 = c2;
	}
	public String getC2_q() {
		return c2_q;
	}
	public void setC2_q(String c2_q) {
		this.c2_q = c2_q;
	}
	public Double getC3() {
		return c3;
	}
	public void setC3(Double c3) {
		this.c3 = c3;
	}
	public String getC3_q() {
		return c3_q;
	}
	public void setC3_q(String c3_q) {
		this.c3_q = c3_q;
	}
	public Double getIc4() {
		return ic4;
	}
	public void setIc4(Double ic4) {
		this.ic4 = ic4;
	}
	public String getIc4_q() {
		return ic4_q;
	}
	public void setIc4_q(String ic4_q) {
		this.ic4_q = ic4_q;
	}
	public Double getNc4() {
		return nc4;
	}
	public void setNc4(Double nc4) {
		this.nc4 = nc4;
	}
	public String getNc4_q() {
		return nc4_q;
	}
	public void setNc4_q(String nc4_q) {
		this.nc4_q = nc4_q;
	}
	public Double getIc5() {
		return ic5;
	}
	public void setIc5(Double ic5) {
		this.ic5 = ic5;
	}
	public String getIc5_q() {
		return ic5_q;
	}
	public void setIc5_q(String ic5_q) {
		this.ic5_q = ic5_q;
	}
	public Double getNc5() {
		return nc5;
	}
	public void setNc5(Double nc5) {
		this.nc5 = nc5;
	}
	public String getNc5_q() {
		return nc5_q;
	}
	public void setNc5_q(String nc5_q) {
		this.nc5_q = nc5_q;
	}
	public Double getC6() {
		return c6;
	}
	public void setC6(Double c6) {
		this.c6 = c6;
	}
	public String getC6_q() {
		return c6_q;
	}
	public void setC6_q(String c6_q) {
		this.c6_q = c6_q;
	}
	public Double getVolumen_seco() {
		return volumen_seco;
	}
	public void setVolumen_seco(Double volumen_seco) {
		this.volumen_seco = volumen_seco;
	}
	public String getVolumen_seco_q() {
		return volumen_seco_q;
	}
	public void setVolumen_seco_q(String volumen_seco_q) {
		this.volumen_seco_q = volumen_seco_q;
	}
	public Double getInicio_transac() {
		return inicio_transac;
	}
	public void setInicio_transac(Double inicio_transac) {
		this.inicio_transac = inicio_transac;
	}
	public String getInicio_transac_q() {
		return inicio_transac_q;
	}
	public void setInicio_transac_q(String inicio_transac_q) {
		this.inicio_transac_q = inicio_transac_q;
	}
	public Double getFin_transac() {
		return fin_transac;
	}
	public void setFin_transac(Double fin_transac) {
		this.fin_transac = fin_transac;
	}
	public String getFin_transac_q() {
		return fin_transac_q;
	}
	public void setFin_transac_q(String fin_transac_q) {
		this.fin_transac_q = fin_transac_q;
	}
	public Double getVolumen_hoy_9300() {
		return volumen_hoy_9300;
	}
	public void setVolumen_hoy_9300(Double volumen_hoy_9300) {
		this.volumen_hoy_9300 = volumen_hoy_9300;
	}
	public String getVolumen_hoy_9300_q() {
		return volumen_hoy_9300_q;
	}
	public void setVolumen_hoy_9300_q(String volumen_hoy_9300_q) {
		this.volumen_hoy_9300_q = volumen_hoy_9300_q;
	}
	public Double getDensidad() {
		return densidad;
	}
	public void setDensidad(Double densidad) {
		this.densidad = densidad;
	}
	public String getDensidad_q() {
		return densidad_q;
	}
	public void setDensidad_q(String densidad_q) {
		this.densidad_q = densidad_q;
	}
	public Double getVolumen_bruto_hoy() {
		return volumen_bruto_hoy;
	}
	public void setVolumen_bruto_hoy(Double volumen_bruto_hoy) {
		this.volumen_bruto_hoy = volumen_bruto_hoy;
	}
	public String getVolumen_bruto_hoy_q() {
		return volumen_bruto_hoy_q;
	}
	public void setVolumen_bruto_hoy_q(String volumen_bruto_hoy_q) {
		this.volumen_bruto_hoy_q = volumen_bruto_hoy_q;
	}
	public Double getVolumen_neto_acumulado() {
		return volumen_neto_acumulado;
	}
	public void setVolumen_neto_acumulado(Double volumen_neto_acumulado) {
		this.volumen_neto_acumulado = volumen_neto_acumulado;
	}
	public String getVolumen_neto_acumulado_q() {
		return volumen_neto_acumulado_q;
	}
	public void setVolumen_neto_acumulado_q(String volumen_neto_acumulado_q) {
		this.volumen_neto_acumulado_q = volumen_neto_acumulado_q;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
	
}
