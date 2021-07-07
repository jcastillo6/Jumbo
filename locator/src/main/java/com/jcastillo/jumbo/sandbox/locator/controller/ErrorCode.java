package com.jcastillo.jumbo.sandbox.locator.controller;

/**
 * Errors used by the api
 * @author jorge castillo
 *
 */
public enum ErrorCode {
	//access errors
	EMPTY_CREDENTIALS(1000,"The credential values are not valid, please check the user and password are requiered"),
	INVALID_USER(1001,"The user name is mandatory"),
	INVALID_PASSWORD(1002,"The password is mandatory"),
	INVALID_CREDENTIALS(1003,"The user or password are incorrect, please check"),
	// api errors
	INVALID_LATITUDE(2001,"The latitude value is not valid"),
	INVALID_LONGITUDE(2002,"The longitude value is not valid"),
	INVALID_STORES(2003,"Invalid stores records"), 
	INVALID_STORE_ID(2004,"Invalid store Id"), 
	NOT_FOUND_STORE(2005,"The store doesnt exist"), 
	ERROR_SAVING_STORES(2006,"An error happen while trying to save the store data");
	

	private int errorCode;
	private String msg;
	
	ErrorCode(int errorCode, String msg) {
		this.errorCode=errorCode;
		this.msg=msg;
				
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public String getMsg() {
		return msg;
	}
	
	
	

}
