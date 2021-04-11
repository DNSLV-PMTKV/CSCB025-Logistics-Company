package com.nbu.logistics.controller;

import com.nbu.logistics.dto.CompanyDetailsDto;
import com.nbu.logistics.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

	@Autowired
	private CompanyService service;

	@PostMapping
	public CompanyDetailsDto createCompany(@RequestBody CompanyDetailsDto dto) {
		return service.createCompany(dto);
	}

	@PutMapping("/{id}")
	public CompanyDetailsDto updateCompany(@RequestBody CompanyDetailsDto dto, @PathVariable Long id) {
		return service.updateCompany(dto, id);
	}

	@GetMapping("/{id}")
	public CompanyDetailsDto getCompany(@PathVariable Long id) {
		return service.getCompany(id);
	}

}
