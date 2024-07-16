package org.delivery.api.domain.user.business;

import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.token.controller.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {

	private final UserService userService;
	private final UserConverter userConverter;
	private final TokenBusiness tokenBusiness;

	public UserResponse register(final UserRegisterRequest request) {
		final User entity = userConverter.toEntity(request);
		final User register = userService.register(entity);
		return userConverter.toResponse(register);
	}

	public TokenResponse login(final UserLoginRequest request) {
		final User user = userService.login(request.email(), request.password());
		final TokenResponse tokenResponse = tokenBusiness.issueToken(user);
		return tokenResponse;
	}

	public UserResponse me(final User user) {
		final UserResponse response = userConverter.toResponse(user);
		return response;
	}
}
