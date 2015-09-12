package ar.com.dccsoft.srytd.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MappedFieldValue extends FieldValue {

	private String code;
	@JsonIgnore
	private Process process;
	private Date dateCreated;
	private String createdBy;

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


}
