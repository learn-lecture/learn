package org.delivery.api.domain.user.controller.model;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
	@NotBlank
	String email,

	@NotBlank
	String password
) {
}
