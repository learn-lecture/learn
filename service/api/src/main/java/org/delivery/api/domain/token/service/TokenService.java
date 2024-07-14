package org.delivery.api.domain.token.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.delivery.api.domain.token.exception.TokenExceptionType;
import org.delivery.api.domain.token.ifs.TokenHelpersIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.api.exception.BadRequestException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenHelpersIfs tokenHelpersIfs;

	public TokenDto issueAccessToken(final Long userId) {
		final HashMap<String, Object> data = new HashMap<>();
		data.put("userId", userId);
		return tokenHelpersIfs.issueAccessToken(data);
	}

	public TokenDto issueRefreshToken(final Long userId) {
		final HashMap<String, Object> data = new HashMap<>();
		data.put("userId", userId);
		return tokenHelpersIfs.issueRefreshToken(data);
	}

	public Long validationToken(final String token) {
		final Map<String, Object> data = tokenHelpersIfs.validationTokenWithThrow(token);
		final Object userId = data.get("userId");
		Objects.requireNonNull(userId, () -> {
			throw new BadRequestException(TokenExceptionType.INVALID_TOKEN_EXCEPTION);
		});

		return Long.parseLong(String.valueOf(userId));
	}

}
