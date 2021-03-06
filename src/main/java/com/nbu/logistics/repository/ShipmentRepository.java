package com.nbu.logistics.repository;

import com.nbu.logistics.entity.Shipment;
import com.nbu.logistics.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
	List<Shipment> findByRegisteredStatusTrue();

	List<Shipment> findByRegisteredStatusTrueAndEmployee(User employee);

	List<Shipment> findByRegisteredStatusTrueAndDeliveredStatusFalse();

	List<Shipment> findByRegisteredStatusTrueAndSender(User sender);

	List<Shipment> findByRegisteredStatusTrueAndTarget(User target);

	List<Shipment> findAllByCreatedTsGreaterThanAndCreatedTsLessThan(Instant from, Instant to);
}
