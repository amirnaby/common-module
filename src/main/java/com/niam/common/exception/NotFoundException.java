package com.niam.common.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }

    public NotFoundException(String responseDescription) {
        super(404, 404, responseDescription);
    }
}