package com.niam.commonservice.exception;

public class LocalDateTimeException extends BusinessException {
    public LocalDateTimeException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseCode, reasonCode, responseDescription);
    }
}