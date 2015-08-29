package ar.com.dccsoft.srytd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MappedFieldValue extends FieldValue {

	private String tag;
	@JsonIgnore
	private Process process;

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

}
