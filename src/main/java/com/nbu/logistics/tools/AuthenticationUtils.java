package com.nbu.logistics.tools;

import com.nbu.logistics.exceptions.OperationFailedException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {
	public static String getAuthenticatedUsername() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new OperationFailedException("Failed to retrieve the currently authenticated user.");
		}
		return authentication.getName();
	}
}
