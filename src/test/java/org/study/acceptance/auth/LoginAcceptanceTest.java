package org.study.acceptance.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.study.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static org.study.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.study.acceptance.utils.AcceptanceTestTemplate;
import org.study.auth.application.dto.LoginRequestDto;

public class LoginAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email1@email.com";

    @BeforeEach
    void setUp() {
        this.cleanUp();
        this.createUser(email);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "password", "token");

        // when
        String token = requestLoginGetToken(dto);

        // then
        assertNotNull(token);
    }

    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNotZero() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "wrong password", "token");

        // when
        Integer code = requestLoginGetResponseCode(dto);

        // then
        assertEquals(code, 400);
    }

}
