package com.usama.flightcheck.exceptoin;

public class BadCredentialsException extends RuntimeException {
	
	
	public BadCredentialsException() {
		super(String.format("Either emailId or Password is Wrong"));
	}
	
}
