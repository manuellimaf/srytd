package ar.com.dccsoft.srytd.utils.errors;

public class ProcessException extends RuntimeException {
	
	private Long errorId;
	
	public ProcessException(Long errorId, String message, Throwable causedBy) {
		super(message, causedBy);
		this.errorId = errorId;
	}
	
	public Long getErrorId() {
		return errorId;
	}
}
