package com.niam.commonservice.exception;

public class PaginationException extends BusinessException {
    public PaginationException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }
}