package com.nbu.logistics.controller;

import com.nbu.logistics.dto.JwtDto;
import com.nbu.logistics.dto.SigninDto;
import com.nbu.logistics.dto.SignupDto;
import com.nbu.logistics.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<Void> registerUser(@RequestBody SignupDto signUpRequest) {
		userService.registerUser(signUpRequest);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/signin")
	public JwtDto authenticateUser(@RequestBody SigninDto loginRequest) {
		return userService.loginUser(loginRequest);
	}

}
