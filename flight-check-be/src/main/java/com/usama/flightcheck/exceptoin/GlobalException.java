package com.usama.flightcheck.exceptoin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(ResourceNotFoundExceptoion.class)
	public ResponseEntity<String> resourceNotFoundExceptoion(ResourceNotFoundExceptoion ex) {
		String msg = ex.getMessage();
		return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> badCredentialsException(BadCredentialsException ex) {
		String msg = ex.getMessage();
		return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
	}
}
