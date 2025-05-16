package com.niam.commonservice.exception;

public class ValidationException extends BusinessException {
    public ValidationException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }
}