package org.delivery.account.domain.token.controller

import org.delivery.account.domain.token.model.TokenDto
import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val accessTokenExpiredAt: LocalDateTime,
    val refreshToken: String,
    val refreshTokenExpiredAt: LocalDateTime
) {

    companion object {
        @JvmStatic
        fun of(accessToken: TokenDto, refreshToken: TokenDto): TokenResponse {
            return TokenResponse(
                accessToken = accessToken.token!!,
                accessTokenExpiredAt = accessToken.expiredAt!!,
                refreshToken = refreshToken.token!!,
                refreshTokenExpiredAt = refreshToken.expiredAt!!
            );
        }
    }

}
