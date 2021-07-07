package com.jcastillo.jumbo.sandbox.locator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionRequestHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionRequestResponse processValidationError(BadRequestException ex) {
        ExceptionRequestResponse badResponse = new ExceptionRequestResponse();
        badResponse.setErrorCode(ex.getErrorCode().getErrorCode());
        badResponse.setErrorMessage(ex.getErrorCode().getMsg());
        return badResponse;
    }
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionRequestResponse NotFoundError(NotFoundException ex) {
        ExceptionRequestResponse badResponse = new ExceptionRequestResponse();
        badResponse.setErrorCode(ex.getErrorCode().getErrorCode());
        badResponse.setErrorMessage(ex.getErrorCode().getMsg());
        return badResponse;
    }

}
