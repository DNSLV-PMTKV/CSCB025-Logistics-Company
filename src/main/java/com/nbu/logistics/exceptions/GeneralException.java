package com.nbu.logistics.exceptions;

public class GeneralException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GeneralException(String message) {
		super(message);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
