package org.delivery.apigateway.account.model

import java.time.LocalDateTime

data class TokenDto(
    var token: String? = null,
    var expiredAt: LocalDateTime? = null
) {

    companion object {
        @JvmStatic
        fun of(token: String, expirationTime: Long?): TokenDto {
            val expiredAt = LocalDateTime.now().plusHours(expirationTime!!)
            return TokenDto(token, expiredAt)
        }
    }

}
