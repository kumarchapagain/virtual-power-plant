package com.progressive.powerplant.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * The GlobalExceptionHandler class is responsible for handling various exceptions that may
 * occur during API requests and providing appropriate responses. It uses Spring's exception
 * handling mechanisms to customize error responses for different types of exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> handleException(HttpServletRequest request, Exception e) {
		Error error = ErrorUtils.createError(request, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handles APIException instances by returning a Bad Request (400) HTTP response
	 * with the error message from the exception.
	 *
	 * @param e The APIException instance.
	 * @return A ResponseEntity containing an Error with the error message.
	 */
	@ExceptionHandler(APIException.class)
	public ResponseEntity<Error> apiException(HttpServletRequest request, APIException e) {
		String message = e.getMessage();
		Error error = ErrorUtils.createError(request, message, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles DataNotFoundException instances by returning a Not Found (404) HTTP response
	 * with the error message from the exception.
	 *
	 * @param e The DataNotFoundException instance.
	 * @return A ResponseEntity containing an Error with the error message.
	 */
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Error> dataNotFoundException(HttpServletRequest request, DataNotFoundException e) {
		String message = e.getMessage();
		Error error = ErrorUtils.createError(request, message, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles MethodArgumentNotValidException instances by returning a Bad Request (400) HTTP response
	 * with validation error messages for fields in the request.
	 *
	 * @param e The MethodArgumentNotValidException instance.
	 * @return A ResponseEntity containing field names as keys and validation error messages as values.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Error> handleValidationExceptions(HttpServletRequest request, MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		Error error = ErrorUtils.createError(request, "Field Validation failed", HttpStatus.BAD_REQUEST.value());
		error.errors = errors;
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
