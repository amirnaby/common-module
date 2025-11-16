package com.niam.common.exception;

public class IllegalArgumentException extends BusinessException {
    public IllegalArgumentException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }

    public IllegalArgumentException(String responseDescription) {
        super(400, 400, responseDescription);
    }
}