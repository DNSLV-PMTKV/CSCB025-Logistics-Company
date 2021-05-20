package com.nbu.logistics.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.nbu.logistics.dto.OfficeDto;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {

	private static final List<Role> employeeRoles = Arrays.asList(Role.ROLE_COURIER, Role.ROLE_OFFICE);

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private OfficeService officeService;

	@Autowired
	PasswordEncoder encoder;

	public UserDto createEmployee(SignupUserDto dto) {
		if (repository.existsByUsername(dto.getUsername())) {
			throw new InvalidInputException("Username is already taken!");
		}

		if (repository.existsByEmail(dto.getEmail())) {
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

		u = repository.save(u);
		UserDto userDto = ObjectConverter.convertObject(u, UserDto.class);
		userDto.setOffice(ObjectConverter.convertObject(office, SimpleOfficeDto.class));
		userDto.setRoles(Arrays.asList(role.getName()));
		return userDto;
	}

	public UserDto updateUser(UserDto userDto, Long id) {
		Optional<User> existing = repository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException("User does not exist.");
		}
		User existingUser = existing.get();

		User authenticated = repository.findByUsername(AuthenticationUtils.getAuthenticatedUsername()).get();

		if (!authenticated.getId().equals(existingUser.getId()) && !authenticated.getUsername().equals("admin")) {
			throw new PermissionDeniedException("You don't have permission to edit.");
		}

		existingUser.setUpdatedTs(Instant.now());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setUsername(userDto.getUsername());
		existingUser.setOffice(ObjectConverter.convertObject(userDto.getOffice(), Office.class));

		repository.save(existingUser);
		return ObjectConverter.convertObject(repository.findById(existingUser.getId()).get(), UserDto.class);
	}

}
