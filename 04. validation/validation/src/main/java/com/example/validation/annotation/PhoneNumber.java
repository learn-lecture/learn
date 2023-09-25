package com.example.validation.annotation;

import com.example.validation.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 사용자 정의 검증 어노테이션 (PhoneNumberValidator.class에서 검증받겠다는 뜻)
@Constraint(validatedBy = {PhoneNumberValidator.class})
// DTO FIELD에만 적용을 하겠다. 추가적으로 Parameter등 더 적용 가능.
@Target({ElementType.FIELD})
// 어노테이션 코드의 메타데이터가 유지(유효한)되는 시간
// 런타임시에만 하겠다. (유지보수)
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "핸드폰 번호 양식에 맞지 않습니다. ex) 010-0000-0000";
    String regexp() default "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
