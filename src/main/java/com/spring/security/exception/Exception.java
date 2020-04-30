package com.spring.security.exception;

public class Exception extends RuntimeException{
	
	 public Exception(String message) {
	        super(message);
	    }

	    public Exception(String code, Throwable cause) {
	        super(cause);
	    }
}
