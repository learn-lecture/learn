package org.delivery.apigateway.account.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
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
