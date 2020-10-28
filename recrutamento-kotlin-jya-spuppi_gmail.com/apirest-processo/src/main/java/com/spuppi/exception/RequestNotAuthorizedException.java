package com.spuppi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RequestNotAuthorizedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RequestNotAuthorizedException(String exception) {
		super(exception);
	}
}
