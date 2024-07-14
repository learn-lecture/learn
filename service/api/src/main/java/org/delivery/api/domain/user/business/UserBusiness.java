package org.delivery.api.domain.user.business;

import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.token.controller.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {

	private final UserService service;
	private final UserConverter converter;
	private final TokenBusiness tokenBusiness;

	public UserResponse register(final UserRegisterRequest request) {
		final UserEntity entity = converter.toEntity(request);
		final UserEntity register = service.register(entity);
		return converter.toResponse(register);
	}

	public TokenResponse login(final UserLoginRequest request) {
		final UserEntity user = service.login(request.email(), request.password());
		final TokenResponse tokenResponse = tokenBusiness.issueToken(user);
		return tokenResponse;
	}
}
