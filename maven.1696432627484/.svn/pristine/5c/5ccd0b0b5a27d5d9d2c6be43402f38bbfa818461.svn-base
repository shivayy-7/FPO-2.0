package com.fpo.web.utils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fpo.web.vos.CustomErrorResponse;



@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// error handle for @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.BAD_REQUEST);

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

	}
	

	//global error handeler for Data  Already Exist in runtime exception 
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<CustomErrorResponse> RuntimeException(RuntimeException ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setError(ex.getMessage());
		errors.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}
	
	//global error handeler for no such element exception 
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<CustomErrorResponse> NoDataFoundException(NoSuchElementException ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setError("Please enter a valid id .");
		errors.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomErrorResponse> Exception(Exception ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setError(ex.getMessage());
		errors.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());

		return new ResponseEntity<>(errors, HttpStatus.SERVICE_UNAVAILABLE);

	}
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<CustomErrorResponse> IllegalStateException(IllegalStateException ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setError(ex.getMessage());
		errors.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());

		return new ResponseEntity<>(errors, HttpStatus.SERVICE_UNAVAILABLE);

	}
	

}
