package com.niam.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final Integer responseCode;
    private final Integer reasonCode;
    private final String responseDescription;

    public BusinessException(Integer responseCode, Integer reasonCode, String responseDescription) {
        super(responseDescription);
        this.responseCode = responseCode;
        this.reasonCode = reasonCode;
        this.responseDescription = responseDescription;
    }
}