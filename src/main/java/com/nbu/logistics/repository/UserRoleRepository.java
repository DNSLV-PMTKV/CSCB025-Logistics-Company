package com.nbu.logistics.repository;

import java.util.Optional;

import com.nbu.logistics.entity.UserRole;
import com.nbu.logistics.enums.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	Optional<UserRole> findByName(Role name);
}
