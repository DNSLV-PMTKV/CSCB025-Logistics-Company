package com.nbu.logistics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DoesNotExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DoesNotExistsException(String message) {
		super(message);
	}
}
