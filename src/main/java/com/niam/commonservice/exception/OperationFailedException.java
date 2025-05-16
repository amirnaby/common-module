package com.niam.commonservice.exception;

public class OperationFailedException extends BusinessException {
    public OperationFailedException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }
}