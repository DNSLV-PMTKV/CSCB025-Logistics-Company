package com.nbu.logistics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nbu.logistics.enums.Role;

@Entity
@Table(name = "roles")
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private Role name;

	public UserRole() {
	}

	public UserRole(Role name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Role getName() {
		return name;
	}

	public void setName(Role name) {
		this.name = name;
	}

}
