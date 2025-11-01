package com.niam.common.exception;

public class PaginationException extends BusinessException {
    public PaginationException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }
}