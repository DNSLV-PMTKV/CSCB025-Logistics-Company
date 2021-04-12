package com.nbu.logistics.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	@Column(name = "created_ts", nullable = false)
	Instant createdTs = Instant.now();

	@Column(name = "updated_ts")
	Instant updatedTs;

	public Instant getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Instant createdTs) {
		this.createdTs = createdTs;
	}

	public Instant getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Instant updatedTs) {
		this.updatedTs = updatedTs;
	}

}
