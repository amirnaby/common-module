package com.niam.common.exception;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }
}