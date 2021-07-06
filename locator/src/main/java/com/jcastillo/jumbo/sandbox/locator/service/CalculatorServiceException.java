package com.jcastillo.jumbo.sandbox.locator.service;

/**
 * 
 */
public class CalculatorServiceException extends Exception {
	
	public CalculatorServiceException(String message) {
		super(message);
	}
	
	public CalculatorServiceException(String message,Throwable e) {
		super(message,e);
	}

}
