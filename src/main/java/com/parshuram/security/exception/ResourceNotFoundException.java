package com.parshuram.security.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resourceName;
	private String fieldName;
	private String studentName;
	private Integer fieldValue;
	
	
	public ResourceNotFoundException(String resourceName,String fieldName,String studentName) {
		super(String.format("%s is not found with %s : %s",resourceName,fieldName,studentName));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.studentName=studentName;
	}
	
	public ResourceNotFoundException(String resourceName,String fieldName,Integer fieldValue) {
		super(String.format("%s is not found with %s : %s",resourceName,fieldName,fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
	
	

}
