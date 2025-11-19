package com.niam.common.exception;

public class ValidationException extends BusinessException {
    public ValidationException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }

    public ValidationException(String responseDescription) {
        super(400, 400, responseDescription);
    }
}