package com.jcastillo.jumbo.sandbox.locator.service;

/**
 * Wrapper exception to handle the common errors
 * @author jorge castillo
 *
 */
public class BadRequestException extends RuntimeException  {
	
	public BadRequestException(String msg) {
		super(msg);
	}



}
