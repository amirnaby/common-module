package com.niam.common.exception;

public class InquiryException extends BusinessException {
    public InquiryException(Integer responseCode, Integer reasonCode, String message) {
        super(responseCode, reasonCode, message);
    }
}