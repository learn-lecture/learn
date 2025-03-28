package org.delivery.api.domain.token.model;

import java.time.LocalDateTime;

public record TokenDto(
	String token,
	LocalDateTime expiredAt
) {

	public static TokenDto of(final String token, final Long expirationTime) {
		final LocalDateTime expiredAt = LocalDateTime.now().plusHours(expirationTime);
		return new TokenDto(token, expiredAt);
	}

}
