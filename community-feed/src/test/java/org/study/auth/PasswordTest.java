package org.study.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.study.auth.domain.Password;

public class PasswordTest {

    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        Password password = Password.createEncryptPassword("password");
        assertTrue(password.matchPassword("password"));
    }

    @Test
    void givenPassword_whenMatchDifferentPassword_thenReturnTrue() {
        Password password = Password.createEncryptPassword("password");
        assertFalse(password.matchPassword("password1"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNullOrEmpty_whenCreate_thenThrowError(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(password));
    }

}
