package com.bae.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="No plant found with that planting month")
public class MonthNotFoundException extends Exception {

	private static final long serialVersionUID = -6281720380638927392L;

	public MonthNotFoundException() {
		super();
	}

	public MonthNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MonthNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MonthNotFoundException(String message) {
		super(message);
	}

	public MonthNotFoundException(Throwable cause) {
		super(cause);
	}


}

