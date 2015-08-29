package ar.com.dccsoft.srytd.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProcessResult {

	private ProcessStatus status;
	private String fileName;
	@JsonIgnore
	private String file;

	private Long sentValues = 0L;
	private Long unsentValues = 0L;
	private Set<ProcessAlert> warnings;

	private ProcessAlert error;
	
	public Long getSentValues() {
		return sentValues;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setSentValues(Long sentValues) {
		this.sentValues = sentValues;
	}

	public Long getUnsentValues() {
		return unsentValues;
	}

	public void setUnsentValues(Long unsentValues) {
		this.unsentValues = unsentValues;
	}

	public Set<ProcessAlert> getWarnings() {
		return warnings;
	}

	public void setWarnings(Set<ProcessAlert> warnings) {
		this.warnings = warnings;
	}

	public ProcessAlert getError() {
		return error;
	}

	public ProcessStatus getStatus() {
		return status;
	}

	public void setStatus(ProcessStatus status) {
		this.status = status;
	}

	public void setError(ProcessAlert error) {
		this.error = error;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
