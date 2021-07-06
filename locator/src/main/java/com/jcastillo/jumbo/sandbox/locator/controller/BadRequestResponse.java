package com.jcastillo.jumbo.sandbox.locator.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BadRequestResponse {
    private int errorCode;
    private String errorMessage;
}
