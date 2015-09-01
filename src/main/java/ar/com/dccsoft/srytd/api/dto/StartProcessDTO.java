package ar.com.dccsoft.srytd.api.dto;

public class StartProcessDTO {

	private String dateFrom;
	private Long hourFrom;

	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Long getHourFrom() {
		return hourFrom;
	}
	public void setHourFrom(Long hourFrom) {
		this.hourFrom = hourFrom;
	}
}
