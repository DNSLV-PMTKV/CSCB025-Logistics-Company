package com.nbu.logistics.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.nbu.logistics.entity.UserRole;
import com.nbu.logistics.enums.Role;

public class UserDto extends BaseDto {
	private Long id;
	private String username;
	private String email;

	private List<Role> roles;
	private SimpleOfficeDto office;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public SimpleOfficeDto getOffice() {
		return office;
	}

	public void setOffice(SimpleOfficeDto office) {
		this.office = office;
	}

}
