package com.nbu.logistics.controller;

import com.nbu.logistics.dto.SignupUserDto;
import com.nbu.logistics.dto.UserDto;
import com.nbu.logistics.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserDto createEmployee(@RequestBody SignupUserDto userDto) {
		return service.createEmployee(userDto);
	}

	@PutMapping("/{id}")
	// @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COURIER', 'ROLE_OFFICE')")
	public UserDto updateEmployee(@RequestBody UserDto userDto, @PathVariable Long id) {
		return service.updateUser(userDto, id);
	}

}
