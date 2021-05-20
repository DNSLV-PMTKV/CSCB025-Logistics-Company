package com.nbu.logistics.dto;

import java.util.List;

import com.nbu.logistics.enums.Role;

public class SignupUserDto extends SignupDto {
	private List<Role> roles;
	private SimpleOfficeDto office;

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
