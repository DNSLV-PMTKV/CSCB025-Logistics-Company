package com.nbu.logistics.repository;

import java.util.List;

import com.nbu.logistics.entity.Office;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {
	boolean existsByCityAndAddress(String city, String address);

	List<Office> findAllByCity(String city);
}
