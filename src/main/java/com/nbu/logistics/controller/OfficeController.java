package com.nbu.logistics.controller;

import java.util.List;

import com.nbu.logistics.dto.OfficeDto;
import com.nbu.logistics.dto.search.SearchOfficeDto;
import com.nbu.logistics.service.OfficeService;

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

@RestController
@RequestMapping("/api/office")
public class OfficeController {
	@Autowired
	private OfficeService service;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public OfficeDto createOffice(@RequestBody OfficeDto officeDto) {
		return service.createOffice(officeDto);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public OfficeDto updateOffice(@RequestBody OfficeDto officeDto, @PathVariable Long id) {
		return service.updateOffice(officeDto, id);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteOffice(@PathVariable Long id) {
		service.deleteOffice(id);
	}

	@GetMapping("/{id}")
	public OfficeDto getOffice(@PathVariable Long id) {
		return service.getOffice(id);
	}

	@GetMapping
	public List<OfficeDto> listOffices() {
		return service.listOffices();
	}

	@PostMapping("/search")
	public List<OfficeDto> searchOffices(@RequestBody SearchOfficeDto searchOfficeDto) {
		return service.searchOffices(searchOfficeDto);
	}

}
