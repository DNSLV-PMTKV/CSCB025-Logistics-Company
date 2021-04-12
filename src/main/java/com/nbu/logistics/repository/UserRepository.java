package com.nbu.logistics.repository;

import java.util.Optional;

import com.nbu.logistics.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);

	void deleteByUsername(String username);
}
