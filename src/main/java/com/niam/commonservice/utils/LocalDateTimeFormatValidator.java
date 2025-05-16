package com.niam.commonservice.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatValidator implements ConstraintValidator<ValidLocalDateTimeFormat, String> {
    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    };

    @Override
    public void initialize(ValidLocalDateTimeFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            LocalDateTime.parse(value, FORMATTERS[0]);
            return true;
        } catch (Exception e) {
            try {
                LocalDateTime.parse(value, FORMATTERS[1]);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }
}