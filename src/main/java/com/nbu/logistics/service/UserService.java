package com.nbu.logistics.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.nbu.logistics.config.UserPrincipal;
import com.nbu.logistics.config.jwt.JwtUtils;
import com.nbu.logistics.dto.JwtDto;
import com.nbu.logistics.dto.OfficeDto;
import com.nbu.logistics.dto.SigninDto;
import com.nbu.logistics.dto.SignupDto;
import com.nbu.logistics.dto.SignupUserDto;
import com.nbu.logistics.dto.SimpleOfficeDto;
import com.nbu.logistics.dto.UserDto;
import com.nbu.logistics.entity.Office;
import com.nbu.logistics.entity.User;
import com.nbu.logistics.entity.UserRole;
import com.nbu.logistics.enums.Role;
import com.nbu.logistics.exceptions.DoesNotExistsException;
import com.nbu.logistics.exceptions.InvalidInputException;
import com.nbu.logistics.exceptions.PermissionDeniedException;
import com.nbu.logistics.repository.UserRepository;
import com.nbu.logistics.repository.UserRoleRepository;
import com.nbu.logistics.tools.AuthenticationUtils;
import com.nbu.logistics.tools.ObjectConverter;

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
@Transactional
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

	private static final List<Role> employeeRoles = Arrays.asList(Role.ROLE_COURIER, Role.ROLE_OFFICE);

	@Autowired
	private OfficeService officeService;

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

	public UserDto createEmployee(SignupUserDto dto) {
		if (userRepository.existsByUsername(dto.getUsername())) {
			throw new InvalidInputException("Username is already taken!");
		}

		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new InvalidInputException("Email is already in use!");
		}

		if (dto.getRoles().size() > 1) {
			throw new InvalidInputException("Please select only one role!");
		}

		if (!employeeRoles.contains(dto.getRoles().get(0))) {
			throw new InvalidInputException("Invalid role assigned!");
		}

		OfficeDto office = officeService.getOffice(dto.getOffice().getId());
		UserRole role = userRoleRepository.findByName(dto.getRoles().get(0)).orElse(null);

		User u = new User();
		u.setCreatedTs(Instant.now());
		u.setEmail(dto.getEmail());
		u.setOffice(ObjectConverter.convertObject(dto.getOffice(), Office.class));
		u.setPassword(encoder.encode(dto.getPassword()));
		u.setUsername(dto.getUsername());
		u.setRoles(Arrays.asList(role));

		User saved = userRepository.save(u);
		UserDto userDto = ObjectConverter.convertObject(saved, UserDto.class);
		userDto.setOffice(ObjectConverter.convertObject(office, SimpleOfficeDto.class));
		userDto.setRoles(Arrays.asList(role.getName()));
		return userDto;
	}

	public UserDto updateUser(UserDto userDto, Long id) {
		Optional<User> existing = userRepository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException("User does not exist.");
		}
		User existingUser = existing.get();

		User authenticated = userRepository.findByUsername(AuthenticationUtils.getAuthenticatedUsername()).get();

		if (!authenticated.getId().equals(existingUser.getId()) && !authenticated.getUsername().equals("admin")) {
			throw new PermissionDeniedException("You don't have permission to edit.");
		}

		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new InvalidInputException("Username is already taken!");
		}

		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new InvalidInputException("Email is already in use!");
		}

		if (userDto.getRoles() == null || userDto.getRoles().size() != 1) {
			throw new InvalidInputException("Please select only one role!");
		}

		if (!employeeRoles.contains(userDto.getRoles().get(0))) {
			throw new InvalidInputException("Invalid role assigned!");
		}

		OfficeDto office = officeService.getOffice(userDto.getOffice().getId());
		UserRole role = userRoleRepository.findByName(userDto.getRoles().get(0)).orElse(null);

		existingUser.setUpdatedTs(Instant.now());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setUsername(userDto.getUsername());
		existingUser.setOffice(ObjectConverter.convertObject(office, Office.class));
		existingUser.updateRoles(Arrays.asList(role));

		User saved = userRepository.save(existingUser);
		UserDto result = ObjectConverter.convertObject(saved, UserDto.class);
		result.setOffice(ObjectConverter.convertObject(office, SimpleOfficeDto.class));
		result.setRoles(Arrays.asList(role.getName()));
		return result;
	}

	public UserDto getUser(Long id) {
		Optional<User> existing = userRepository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException("User does not exist.");
		}
		UserDto u = ObjectConverter.convertObject(existing.get(), UserDto.class);
		u.setRoles(existing.get().getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));
		return u;
	}

	public void deleteUser(Long id) {
		Optional<User> existing = userRepository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException("User does not exist.");
		}
		User existingUser = existing.get();

		User authenticated = userRepository.findByUsername(AuthenticationUtils.getAuthenticatedUsername()).get();

		if (!authenticated.getId().equals(existingUser.getId()) && !authenticated.getUsername().equals("admin")) {
			throw new PermissionDeniedException("You don't have permission to edit.");
		}

		userRepository.delete(existingUser);

	}
}
