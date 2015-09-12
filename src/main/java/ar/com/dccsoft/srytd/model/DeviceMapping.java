package ar.com.dccsoft.srytd.model;

import java.util.Date;

public class DeviceMapping {

	private Long id;
	/** Device identifier used by company **/
	private String name;
	/** Tag code assigned by authority **/
	private String code;
	
	private Date creationDate;
	private String createdBy;

	private String presion;
	private String temperatura;
	private String caudal_horario;
	private String volumen_bruto_acumulado;
	private String volumen_neto_hoy;
	private String caudal_horario_9300;
	private String volumen_acumulado_9300;
	private String volumen_desplazado;
	private String altura_liquida;
	private String mf;
	private String ctl;
	private String cpl;
	private String factor_k;
	private String pulsos_brutos;
	private String fcv;
	private String ctsh;
	private String porcentaje_agua;
	private String poder_calorifico;
	private String densidad_relativa;
	private String co2;
	private String n2;
	private String sh2;
	private String c1;
	private String c2;
	private String c3;
	private String ic4;
	private String nc4;
	private String ic5;
	private String nc5;
	private String c6;
	private String volumen_seco;
	private String inicio_transac;
	private String fin_transac;
	private String volumen_hoy_9300;
	private String densidad;
	private String volumen_bruto_hoy;
	private String volumen_neto_acumulado;
	
	
	
	public String getPresion() {
		return presion;
	}

	public void setPresion(String presion) {
		this.presion = presion;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getCaudal_horario() {
		return caudal_horario;
	}

	public void setCaudal_horario(String caudal_horario) {
		this.caudal_horario = caudal_horario;
	}

	public String getVolumen_bruto_acumulado() {
		return volumen_bruto_acumulado;
	}

	public void setVolumen_bruto_acumulado(String volumen_bruto_acumulado) {
		this.volumen_bruto_acumulado = volumen_bruto_acumulado;
	}

	public String getVolumen_neto_hoy() {
		return volumen_neto_hoy;
	}

	public void setVolumen_neto_hoy(String volumen_neto_hoy) {
		this.volumen_neto_hoy = volumen_neto_hoy;
	}

	public String getCaudal_horario_9300() {
		return caudal_horario_9300;
	}

	public void setCaudal_horario_9300(String caudal_horario_9300) {
		this.caudal_horario_9300 = caudal_horario_9300;
	}

	public String getVolumen_acumulado_9300() {
		return volumen_acumulado_9300;
	}

	public void setVolumen_acumulado_9300(String volumen_acumulado_9300) {
		this.volumen_acumulado_9300 = volumen_acumulado_9300;
	}

	public String getVolumen_desplazado() {
		return volumen_desplazado;
	}

	public void setVolumen_desplazado(String volumen_desplazado) {
		this.volumen_desplazado = volumen_desplazado;
	}

	public String getAltura_liquida() {
		return altura_liquida;
	}

	public void setAltura_liquida(String altura_liquida) {
		this.altura_liquida = altura_liquida;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	public String getCtl() {
		return ctl;
	}

	public void setCtl(String ctl) {
		this.ctl = ctl;
	}

	public String getCpl() {
		return cpl;
	}

	public void setCpl(String cpl) {
		this.cpl = cpl;
	}

	public String getFactor_k() {
		return factor_k;
	}

	public void setFactor_k(String factor_k) {
		this.factor_k = factor_k;
	}

	public String getPulsos_brutos() {
		return pulsos_brutos;
	}

	public void setPulsos_brutos(String pulsos_brutos) {
		this.pulsos_brutos = pulsos_brutos;
	}

	public String getFcv() {
		return fcv;
	}

	public void setFcv(String fcv) {
		this.fcv = fcv;
	}

	public String getCtsh() {
		return ctsh;
	}

	public void setCtsh(String ctsh) {
		this.ctsh = ctsh;
	}

	public String getPorcentaje_agua() {
		return porcentaje_agua;
	}

	public void setPorcentaje_agua(String porcentaje_agua) {
		this.porcentaje_agua = porcentaje_agua;
	}

	public String getPoder_calorifico() {
		return poder_calorifico;
	}

	public void setPoder_calorifico(String poder_calorifico) {
		this.poder_calorifico = poder_calorifico;
	}

	public String getDensidad_relativa() {
		return densidad_relativa;
	}

	public void setDensidad_relativa(String densidad_relativa) {
		this.densidad_relativa = densidad_relativa;
	}

	public String getCo2() {
		return co2;
	}

	public void setCo2(String co2) {
		this.co2 = co2;
	}

	public String getN2() {
		return n2;
	}

	public void setN2(String n2) {
		this.n2 = n2;
	}

	public String getSh2() {
		return sh2;
	}

	public void setSh2(String sh2) {
		this.sh2 = sh2;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getIc4() {
		return ic4;
	}

	public void setIc4(String ic4) {
		this.ic4 = ic4;
	}

	public String getNc4() {
		return nc4;
	}

	public void setNc4(String nc4) {
		this.nc4 = nc4;
	}

	public String getIc5() {
		return ic5;
	}

	public void setIc5(String ic5) {
		this.ic5 = ic5;
	}

	public String getNc5() {
		return nc5;
	}

	public void setNc5(String nc5) {
		this.nc5 = nc5;
	}

	public String getC6() {
		return c6;
	}

	public void setC6(String c6) {
		this.c6 = c6;
	}

	public String getVolumen_seco() {
		return volumen_seco;
	}

	public void setVolumen_seco(String volumen_seco) {
		this.volumen_seco = volumen_seco;
	}

	public String getInicio_transac() {
		return inicio_transac;
	}

	public void setInicio_transac(String inicio_transac) {
		this.inicio_transac = inicio_transac;
	}

	public String getFin_transac() {
		return fin_transac;
	}

	public void setFin_transac(String fin_transac) {
		this.fin_transac = fin_transac;
	}

	public String getVolumen_hoy_9300() {
		return volumen_hoy_9300;
	}

	public void setVolumen_hoy_9300(String volumen_hoy_9300) {
		this.volumen_hoy_9300 = volumen_hoy_9300;
	}

	public String getDensidad() {
		return densidad;
	}

	public void setDensidad(String densidad) {
		this.densidad = densidad;
	}

	public String getVolumen_bruto_hoy() {
		return volumen_bruto_hoy;
	}

	public void setVolumen_bruto_hoy(String volumen_bruto_hoy) {
		this.volumen_bruto_hoy = volumen_bruto_hoy;
	}

	public String getVolumen_neto_acumulado() {
		return volumen_neto_acumulado;
	}

	public void setVolumen_neto_acumulado(String volumen_neto_acumulado) {
		this.volumen_neto_acumulado = volumen_neto_acumulado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
