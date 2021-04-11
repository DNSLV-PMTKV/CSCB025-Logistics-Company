package com.nbu.logistics.repository;

import com.nbu.logistics.entity.Company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	Boolean existsByName(String name);
}
