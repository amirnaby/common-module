package com.niam.commonservice.exception;

import com.niam.commonservice.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {
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
    public ResponseEntity<?> handleByteArrayEndpointException(Exception e, HandlerMethod handlerMethod) {
        logger.error(e.getMessage(), e);
        if (handlerMethod.getMethod().getReturnType() == byte[].class) {
            ErrorResponse error = ErrorResponse.builder()
                    .responseCode(500)
                    .reasonCode(500)
                    .responseDescription(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(error);
        }
        return null;
    }
}