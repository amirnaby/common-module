package com.niam.common.exception;

import com.niam.common.model.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e, HttpServletRequest request) {
        ErrorResponse body = ErrorResponse.builder()
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .reasonCode(HttpStatus.INTERNAL_SERVER_ERROR.series().value())
                .responseDescription("Business error")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        ErrorResponse body = ErrorResponse.builder()
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .reasonCode(HttpStatus.INTERNAL_SERVER_ERROR.series().value())
                .responseDescription(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        return ResponseEntity.status(500).body(body);
    }
}