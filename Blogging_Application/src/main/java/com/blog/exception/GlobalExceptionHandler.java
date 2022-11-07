package com.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.utils.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Response> resourceNotFoundExceptionHandler(
			ResourceNotFoundException resourceNotFoundException) {
		LOGGER.info(
				"Inside resourceNotFoundExceptionHandler method in GlobalExceptionHandler started with resourceNotFoundException: {}",
				resourceNotFoundException);
		return new ResponseEntity<Response>(new Response("false", resourceNotFoundException.getMessage(), null),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException argumentNotValidException) {
		LOGGER.info(
				"Inside methodArgumentNotValidExceptionHandler method in GlobalExceptionHandler started with argumentNotValidException: {}",
				argumentNotValidException);
		Map<String, String> map = new HashMap<>();
		argumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			map.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<Response> apiExceptionHandler(ApiException apiException) {
		LOGGER.info("Inside apiExceptionHandler method in GlobalExceptionHandler started with apiException: {}",
				apiException);
		return new ResponseEntity<Response>(new Response("false", apiException.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

}
