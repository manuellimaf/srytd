package ar.com.dccsoft.srytd.api.dto;

public class RoleDTO {
	
	private String id;
	private String name;
	
	public RoleDTO() {}
	
	public RoleDTO(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
