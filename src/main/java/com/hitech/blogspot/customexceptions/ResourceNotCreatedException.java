package com.hitech.blogspot.customexceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotCreatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private String fieldValue;

	public ResourceNotCreatedException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s with  %s and  %s is not created", resourceName, fieldName, fieldValue));
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.resourceName = resourceName;

	}

	public ResourceNotCreatedException(String resourceName) {
		super(String.format("%s is not created, please ensure that you dont enter null values.", resourceName));

		this.resourceName = resourceName;

	}
}
