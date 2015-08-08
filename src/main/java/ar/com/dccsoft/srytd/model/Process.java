package ar.com.dccsoft.srytd.model;

import java.util.Date;


public class Process {

	private Long id;
	private String startedBy;
	private Date startDate;
	private ProcessStatus status;
	private Date readingsFrom;
	
	
	public Date getReadingsFrom() {
		return readingsFrom;
	}
	public void setReadingsFrom(Date readingsFrom) {
		this.readingsFrom = readingsFrom;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStartedBy() {
		return startedBy;
	}
	public void setStartedBy(String startedBy) {
		this.startedBy = startedBy;
	}
	public ProcessStatus getStatus() {
		return status;
	}
	public void setStatus(ProcessStatus status) {
		this.status = status;
	}
	
}
