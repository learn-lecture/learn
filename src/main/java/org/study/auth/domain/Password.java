package org.study.auth.domain;

import com.mysema.commons.lang.Assert;
import lombok.Getter;

public class Password {

    private final String encryptedPassword;

    private Password(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public static Password createEncryptPassword(String password) {
        Assert.hasText(password, "패스워드는 빈 값 일 수 없습니다.");
        return new Password(SHA256.encrypt(password));
    }

    public static Password createPassword(String password) {
        Assert.hasText(password, "패스워드는 빈 값 일 수 없습니다.");
        return new Password(password);
    }

    public boolean matchPassword(String password) {
        return encryptedPassword.matches(SHA256.encrypt(password));
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

}
