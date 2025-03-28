package org.study.auth.domain;

import com.mysema.commons.lang.Assert;
import java.util.regex.Pattern;

public class Email {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    private final String emailText;

    private Email(String email) {
        this.emailText = email;
    }

    public static Email createEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email은 빈 값 일 수 없습니다.");
        }
        if (!PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("유효하지 않은 이메일입니다.");
        }

        return new Email(email);
    }

    public String getEmailText() {
        return emailText;
    }

}
