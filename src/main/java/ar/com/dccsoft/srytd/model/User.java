package ar.com.dccsoft.srytd.model;

import java.util.Date;

public class User {

	private Long id;
	private String username;
	private String password;
	private Boolean enabled;
	private Date dateCreated;
	private String createdBy;
	private Role role;

	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	/**
	 * Role hierarchy (X -> Y reads X is Y)
	 * ADMIN -> USER -> READ_ONLY
	 *       
	 */
	public boolean hasRole(String someRoleName) {
		Role someRole = Role.valueOf(someRoleName);
		if(Role.ADMIN.equals(role))
			return true;
		if(Role.USER.equals(role) && !Role.ADMIN.equals(someRole))
			return true;
		if(Role.READ_ONLY.equals(role) && Role.READ_ONLY.equals(someRole))
			return true;
		
		return false;
	}
	
}
