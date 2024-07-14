package org.delivery.api.domain.token.controller.model;

import java.time.LocalDateTime;

import org.delivery.api.domain.token.model.TokenDto;

import lombok.Builder;

@Builder
public record TokenResponse(
	String accessToken,
	LocalDateTime accessTokenExpiredAt,
	String refreshToken,
	LocalDateTime refreshTokenExpiredAt
) {
	public static TokenResponse of(final TokenDto accessToken, final TokenDto refreshToken) {
		return TokenResponse.builder()
			.accessToken(accessToken.token())
			.accessTokenExpiredAt(accessToken.expiredAt())
			.refreshToken(refreshToken.token())
			.refreshTokenExpiredAt(refreshToken.expiredAt())
			.build();
	}
}
