package org.delivery.api.domain.user.converter;

import java.util.Optional;

import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.db.user.UserEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class UserConverter {

	public UserEntity toEntity(final UserRegisterRequest request) {
		return Optional.ofNullable(request)
			.map(it -> UserEntity.builder()
				.name(request.name())
				.email(request.email())
				.address(request.address())
				.password(request.password())
				.build()
			).orElseThrow();
	}

	public UserResponse toResponse(final UserEntity entity) {
		return Optional.ofNullable(entity)
			.map(it -> UserResponse.from(entity))
			.orElseThrow();
	}
}
