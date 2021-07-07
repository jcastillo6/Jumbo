package com.jcastillo.jumbo.sandbox.locator.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class NotFoundException extends RuntimeException{
	
	private ErrorCode errorCode;
	
		

}
