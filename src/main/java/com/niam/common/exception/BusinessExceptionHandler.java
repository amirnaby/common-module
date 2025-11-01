package com.niam.common.exception;

import com.niam.common.model.response.ErrorResponse;
import com.niam.common.utils.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageUtil messageUtil;
    public BusinessExceptionHandler(MessageUtil messageUtil) {
        this.messageUtil = messageUtil;
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e, HandlerMethod handlerMethod) {
        logger.error(e.getMessage(), e);
        if (handlerMethod.getMethod().getReturnType() == byte[].class) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .responseCode(e.getResponseCode())
                    .reasonCode(e.getReasonCode())
                    .responseDescription(e.getResponseDescription())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }
        return null;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.internalServerError().body(
                ErrorResponse.builder()
                        .responseCode(500)
                        .reasonCode(500)
                        .responseDescription(e.getMessage())
                        .build()
        );
    }
}