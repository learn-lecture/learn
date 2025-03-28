package com.study.jwt.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtService {

	private static final String secretKey = "MyJwtSecretKeyTokenNeedTo256bitsSoIamWriting";

	public String create(
		final Map<String, Object> claims,
		final int expireAt
	) {
		final Date now = new Date();

		final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

		return Jwts.builder()
			.signWith(key, SignatureAlgorithm.HS256)
			.claims(claims)
			.expiration(new Date(now.getTime() + expireAt))
			.issuedAt(now)
			.compact();
	}

	public void validation(final String token) {
		final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
		final Jws<Claims> jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

		jws.getPayload().entrySet().forEach(value -> {
			log.info("key : {}, value : {}", value.getKey(), value.getValue());
		});
	}

}
