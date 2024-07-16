package org.delivery.api.domain.token.helper;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.delivery.api.domain.token.ifs.TokenHelpersIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.api.domain.token.model.info.AuthExceptionType;
import org.delivery.api.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenHelper implements TokenHelpersIfs {

	private final SecretKey secretKey;
	private final Long accessTokenPlusHour;

	private final Long refreshTokenPlusHour;

	public JwtTokenHelper(
		@Value("${token.secret.key}") final String secretKey,
		@Value("${token.access-token.plus-hour}") final Long accessTokenPlusHour,
		@Value("${token.refresh-token.plus-hour}") final Long refreshTokenPlusHour
	) {
		this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		this.accessTokenPlusHour = accessTokenPlusHour;
		this.refreshTokenPlusHour = refreshTokenPlusHour;
	}

	@Override
	public TokenDto issueAccessToken(final Map<String, Object> data) {
		final Date now = new Date();

		final String token = Jwts.builder()
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.claims(data)
			.expiration(new Date(now.getTime() + accessTokenPlusHour))
			.issuedAt(now)
			.compact();

		return TokenDto.of(token, accessTokenPlusHour);
	}

	@Override
	public TokenDto issueRefreshToken(final Map<String, Object> data) {
		final Date now = new Date();

		final String token = Jwts.builder()
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.claims(data)
			.expiration(new Date(now.getTime() + refreshTokenPlusHour))
			.issuedAt(now)
			.compact();

		return TokenDto.of(token, refreshTokenPlusHour);
	}

	@Override
	public Map<String, Object> validationTokenWithThrow(final String token) {
		final Jws<Claims> result;
		try {
			result = Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token);
			return new HashMap<>(result.getPayload());
		} catch (final SignatureException e) {
			throw new BadRequestException(AuthExceptionType.UNSUPPORTED_TOKEN);
		} catch (final ExpiredJwtException e) {
			throw new IllegalArgumentException(e);
		} catch (final UnsupportedJwtException e) {
			throw new BadRequestException(AuthExceptionType.SIGNATURE_TOKEN);
		} catch (final MalformedJwtException e) {
			throw new BadRequestException(AuthExceptionType.EXPIRED_TOKEN);
		} catch (final Exception e) {
			throw new BadRequestException(AuthExceptionType.UNKNOWN_TOKEN);
		}
	}

}
