package com.nbu.logistics.dto;

import java.time.Instant;

public class BaseDto {
	private Instant createdTs = Instant.now();
	private Instant updatedTs;

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
