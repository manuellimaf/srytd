package ar.com.dccsoft.srytd.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MappedFieldValue extends FieldValue {

	private String tag;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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
