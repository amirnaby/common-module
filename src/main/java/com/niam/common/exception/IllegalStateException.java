package com.niam.common.exception;

public class IllegalStateException extends BusinessException {
    public IllegalStateException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }

    public IllegalStateException(String responseDescription) {
        super(400, 400, responseDescription);
    }
}