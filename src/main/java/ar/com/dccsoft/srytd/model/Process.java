package ar.com.dccsoft.srytd.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Process {

	private Long id;
	private String startedBy;
	private Date startDate;
	private ProcessStatus status;
	private Date valuesFrom;
	@JsonIgnore
	private ProcessResult result = new ProcessResult();

	public ProcessResult getResult() {
		return result;
	}

	public void setResult(ProcessResult result) {
		this.result = result;
	}

	public Date getValuesFrom() {
		return valuesFrom;
	}

	public void setValuesFrom(Date valuesFrom) {
		this.valuesFrom = valuesFrom;
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
