package com.example.validation.annotation;

import com.example.validation.validator.YearMonthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = { YearMonthValidator.class })
@NotNull
public @interface YearMonth {
    String pattern() default "yyyy-MM-dd HH:mm:ss";
    // Default interface Method들
    String message() default "입력형식 예시) yyyy-MM-dd HH:mm:ss";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
