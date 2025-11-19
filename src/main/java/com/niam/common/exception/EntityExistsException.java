package com.niam.common.exception;

public class EntityExistsException extends BusinessException {
    public EntityExistsException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }

    public EntityExistsException(String responseDescription) {
        super(400, 400, responseDescription);
    }
}