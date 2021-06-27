package com.nbu.logistics.controller;

import com.nbu.logistics.dto.SignupUserDto;
import com.nbu.logistics.dto.UserDto;
import com.nbu.logistics.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserDto createEmployee(@RequestBody SignupUserDto userDto) {
		return service.createEmployee(userDto);
	}

	@PutMapping("/{id}")
	public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
		return service.updateUser(userDto, id);
	}

	@GetMapping
	public List<UserDto> getUsers() {
		return service.getUsers();
	}

	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable Long id) {
		return service.getUser(id);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
	}

}
