package com.niam.commonservice.exception;

public class DuplicateEntityFoundException extends BusinessException {
    public DuplicateEntityFoundException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }
}