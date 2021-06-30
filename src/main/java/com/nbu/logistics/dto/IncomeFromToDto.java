package com.nbu.logistics.dto;

import java.time.Instant;

public class IncomeFromToDto {
	private Instant fromDate;
	private Instant toDate;

	public Instant getFromDate() {
		return fromDate;
	}

	public void setFromDate(Instant fromDate) {
		this.fromDate = fromDate;
	}

	public Instant getToDate() {
		return toDate;
	}

	public void setToDate(Instant toDate) {
		this.toDate = toDate;
	}

}
