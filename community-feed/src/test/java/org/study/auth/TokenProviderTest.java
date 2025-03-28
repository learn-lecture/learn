package org.study.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.study.auth.domain.TokenProvider;

public class TokenProviderTest {

    private final String secretKey = "test".repeat(128);
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    @Test
    void givenValidUserAndRole_whenCreateToken_thenReturnValidToken() {
        // given
        Long userId = 1L;
        String role = "ADMIN";

        // when
        String token = tokenProvider.createToken(userId, role);

        // then
        assertNotNull(token);
        assertEquals(tokenProvider.getUserId(token), userId);
        assertEquals(tokenProvider.getUserRole(token), role);
    }

    @Test
    void givenInvalidToken_whenGetUserId_thenThrowError() {
        // given
        String invalidToken = "invalidToken";

        // when & then
        assertThrows(Exception.class, () -> tokenProvider.getUserId(invalidToken));
    }

}
