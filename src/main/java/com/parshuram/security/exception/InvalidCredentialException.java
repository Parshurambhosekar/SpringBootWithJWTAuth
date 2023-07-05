package com.parshuram.security.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidCredentialException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	
	public InvalidCredentialException(String message) {
		
		super(String.format(message));
		
		this.message=message;
		
		
	}

}
