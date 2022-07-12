package com.hitech.blogspot.globalexceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hitech.blogspot.customexceptions.ResourceNotCreatedException;
import com.hitech.blogspot.customexceptions.ResourceNotFoundException;
import com.hitech.blogspot.util.ApiResponse;

@RestControllerAdvice
public class GlobleExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
		String message = exception.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ResourceNotCreatedException.class)
	public ResponseEntity<ApiResponse> resourceNotCretedExceptionHandler(ResourceNotCreatedException exception) {
		String message = exception.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> arguementDoesntMatchExceptionHandler(
			MethodArgumentNotValidException exception) {
		Map<String, String> errorMap = new HashMap<>();

		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String defaultMessage = error.getDefaultMessage();
			String field = ((FieldError) error).getField();
			errorMap.put(field, defaultMessage);
		});
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResponse> httpRequestMethodNotSupportedExceptionHandler(
			HttpRequestMethodNotSupportedException exception) {
		String message = exception.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.BAD_REQUEST);

	}

}
