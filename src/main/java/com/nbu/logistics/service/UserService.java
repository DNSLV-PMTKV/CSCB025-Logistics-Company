package com.nbu.logistics.service;

import java.util.Arrays;

import com.nbu.logistics.config.UserPrincipal;
import com.nbu.logistics.config.jwt.JwtUtils;
import com.nbu.logistics.dto.JwtDto;
import com.nbu.logistics.dto.SigninDto;
import com.nbu.logistics.dto.SignupDto;
import com.nbu.logistics.entity.User;
import com.nbu.logistics.entity.UserRole;
import com.nbu.logistics.enums.Role;
import com.nbu.logistics.exceptions.InvalidInputException;
import com.nbu.logistics.repository.UserRepository;
import com.nbu.logistics.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return new UserPrincipal(user);
	}

	public void registerClientUser(SignupDto signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new InvalidInputException("Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new InvalidInputException("Email is already in use!");
		}

		User user = new User();

		UserRole role = userRoleRepository.findByName(Role.ROLE_CLIENT).orElse(null);

		user.setRoles(Arrays.asList(role));
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));

		userRepository.save(user);
	}

	public JwtDto loginUser(SigninDto loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

		return new JwtDto(jwt, userDetails.getUserId(), userDetails.getUsername(), userDetails.getEmail(),
				userDetails.getRolesAsString());
	}
}
