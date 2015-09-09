package ar.com.dccsoft.srytd.model;

import java.math.BigDecimal;
import java.util.Date;

public class TagValue {

	private String tagname;
	private String retrievalMode;
	private Long cycleCount;
	private String version;
	private Date datetime;
	private Date startDatetime;
	private BigDecimal value;
	private BigDecimal vvalue;
	private String quality;
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getRetrievalMode() {
		return retrievalMode;
	}
	public void setRetrievalMode(String retrievalMode) {
		this.retrievalMode = retrievalMode;
	}
	public Long getCycleCount() {
		return cycleCount;
	}
	public void setCycleCount(Long cycleCount) {
		this.cycleCount = cycleCount;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public Date getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getVvalue() {
		return vvalue;
	}
	public void setVvalue(BigDecimal vvalue) {
		this.vvalue = vvalue;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	
}
