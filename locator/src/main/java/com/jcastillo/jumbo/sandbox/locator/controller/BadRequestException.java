package com.jcastillo.jumbo.sandbox.locator.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Wrapper exception to handle the common errors
 * @author jorge castillo
 *
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BadRequestException extends RuntimeException  {
	
    private ErrorCode errorCode; 
     
    
	public BadRequestException(String msg) {
		super(msg);
	}



}
