package com.niam.common.exception;

import com.niam.common.model.response.ErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorResponse body = ErrorResponse.builder()
                .responseCode(e.getResponseCode())
                .reasonCode(e.getReasonCode())
                .responseDescription(e.getMessage())
                .build();
        return ResponseEntity.status(e.getResponseCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    @ExceptionHandler({jakarta.validation.ValidationException.class, com.niam.common.exception.ValidationException.class})
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {
        ErrorResponse body = ErrorResponse.builder()
                .responseCode(HttpStatus.BAD_REQUEST.value())
                .reasonCode(HttpStatus.BAD_REQUEST.series().value())
                .responseDescription("Validation error! " + e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse body = ErrorResponse.builder()
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .reasonCode(HttpStatus.INTERNAL_SERVER_ERROR.series().value())
                .responseDescription(e.getLocalizedMessage())
                .build();
        return ResponseEntity.internalServerError().body(body);
    }
}