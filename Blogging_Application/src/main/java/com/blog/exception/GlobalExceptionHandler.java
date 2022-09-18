package com.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.utils.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Response> resourceNotFoundExceptionHandler(
			ResourceNotFoundException resourceNotFoundException) {
		return new ResponseEntity<Response>(new Response("false", resourceNotFoundException.getMessage(), null),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException argumentNotValidException) {
		Map<String, String> map = new HashMap<>();
		argumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			map.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}

}
