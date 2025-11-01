package com.niam.common.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocalDateTimeFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLocalDateTimeFormat {
    String message() default "DATETIME_IS_NOT_OK";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}