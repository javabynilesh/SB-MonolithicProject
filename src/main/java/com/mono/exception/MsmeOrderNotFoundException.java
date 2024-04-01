package com.mono.exception;

public class MsmeOrderNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MsmeOrderNotFoundException() {
		super();
	}
	
	public MsmeOrderNotFoundException(String message) {
		super(message);
	}

}
