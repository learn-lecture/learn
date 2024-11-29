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
        Assert.hasText(email, "email is not valid");
        Assert.isTrue(PATTERN.matcher(email).matches(), "email is not valid");

        return new Email(email);
    }

    public String getEmailText() {
        return emailText;
    }

}
