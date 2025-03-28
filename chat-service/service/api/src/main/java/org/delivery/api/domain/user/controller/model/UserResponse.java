package org.delivery.api.domain.user.controller.model;

import java.time.LocalDateTime;

import org.delivery.db.user.User;
import org.delivery.db.user.vo.UserStatus;

public record UserResponse(
	Long id,
	String name,
	String email,
	UserStatus status,
	String address,
	LocalDateTime registeredAt,
	LocalDateTime unregisteredAt,
	LocalDateTime lastLoginAt
) {

	public static UserResponse from(final User entity) {
		return new UserResponse(
			entity.getId(),
			entity.getName(),
			entity.getEmail(),
			entity.getStatus(),
			entity.getAddress(),
			entity.getRegisteredAt(),
			entity.getUnregisteredAt(),
			entity.getLastLoginAt()
		);
	}

}
