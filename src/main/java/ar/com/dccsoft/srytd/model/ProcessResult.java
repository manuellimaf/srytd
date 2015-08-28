package ar.com.dccsoft.srytd.model;

public class ProcessResult {

	private Long id;
	private String status;
	private String fileName;
	private String unmappedDevices;
	private String errorDesc;
	private String errorId;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnmappedDevices() {
		return unmappedDevices;
	}

	public void setUnmappedDevices(String unmappedDevices) {
		this.unmappedDevices = unmappedDevices;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

}
