package com.bae.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="No plant found with that id")
public class PlantsNotFoundException extends Exception{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1503572239665288456L;

	public PlantsNotFoundException() {
		super();
		
	}

	public PlantsNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public PlantsNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PlantsNotFoundException(String message) {
		super(message);
		
	}

	public PlantsNotFoundException(Throwable cause) {
		super(cause);
		
	}
	
	

	
}

