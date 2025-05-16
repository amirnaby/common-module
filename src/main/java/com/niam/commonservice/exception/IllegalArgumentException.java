package com.niam.commonservice.exception;

public class IllegalArgumentException extends BusinessException {
    public IllegalArgumentException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }
}