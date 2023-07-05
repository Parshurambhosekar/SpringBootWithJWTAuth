package com.parshuram.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
		
		String message = exception.getMessage();
		
		ApiResponse response=new ApiResponse(message, true);
		
		return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<ApiResponse> handelInvalidCredentialException(InvalidCredentialException exception){
		
		String message = exception.getMessage();
		
		ApiResponse response=new ApiResponse(message, true);
			
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_ACCEPTABLE);
		
		}
		
	
	}
	
	
	
	

