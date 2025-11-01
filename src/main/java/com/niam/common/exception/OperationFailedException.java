package com.niam.common.exception;

public class OperationFailedException extends BusinessException {
    public OperationFailedException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }
}