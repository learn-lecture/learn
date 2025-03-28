package org.delivery.account.domain.token.helper

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.delivery.account.domain.token.ifs.TokenHelperIfs
import org.delivery.account.domain.token.model.TokenDto
import org.delivery.account.domain.token.model.infos.AuthExceptionType
import org.delivery.common.exception.model.BadRequestException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenHelper(
    @Value("\${token.secret.key}") secretKey: String,
    @Value("\${token.access-token.plus-hour}") private val accessTokenPlusHour: Long,
    @Value("\${token.refresh-token.plus-hour}") private val refreshTokenPlusHour: Long
) : TokenHelperIfs {

    private val secretKey: SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

    override fun issueAccessToken(data: Map<String, Any>?): TokenDto? {
        val now = Date()

        val token = Jwts.builder()
            .signWith(secretKey)
            .claims(data)
            .expiration(Date(now.time + accessTokenPlusHour))
            .issuedAt(now)
            .compact()

        return TokenDto.of(token, accessTokenPlusHour)
    }

    override fun issueRefreshToken(data: Map<String, Any>?): TokenDto? {
        val now = Date()

        val token = Jwts.builder()
            .signWith(secretKey)
            .claims(data)
            .expiration(Date(now.time + refreshTokenPlusHour))
            .issuedAt(now)
            .compact()

        return TokenDto.of(token, refreshTokenPlusHour)
    }

    override fun validationTokenWithThrow(token: String?): Map<String, Any>? {
        return try {
            val result = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
            HashMap(result.payload)
        } catch (e: SignatureException) {
            throw BadRequestException(AuthExceptionType.UNSUPPORTED_TOKEN)
        } catch (e: ExpiredJwtException) {
            throw IllegalArgumentException(e)
        } catch (e: UnsupportedJwtException) {
            throw BadRequestException(AuthExceptionType.SIGNATURE_TOKEN)
        } catch (e: MalformedJwtException) {
            throw BadRequestException(AuthExceptionType.EXPIRED_TOKEN)
        } catch (e: Exception) {
            throw BadRequestException(AuthExceptionType.UNKNOWN_TOKEN)
        }
    }

}