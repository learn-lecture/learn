package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {
    private String pattern;

    // YearMonth의 pattern 값을 initialize
    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            // DateFormat이 옳으면 유효한 DateType이라고 식별.
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(this.pattern);
            LocalDateTime localDateTime = LocalDateTime.parse(value, dateTimeFormatter);
            System.out.println(localDateTime);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
