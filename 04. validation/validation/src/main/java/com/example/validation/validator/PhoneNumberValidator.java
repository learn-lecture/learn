package com.example.validation.validator;

import com.example.validation.annotation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private String regexp;
    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       // Pattern pattern = Pattern.compile(regexp);
       // boolean result = pattern.matcher(value).matches();
        // value를 regexp(정규식)과 매치, 옳은 정규식이면 true
        boolean result = Pattern.matches(regexp, value);
        return result;
    }
}
