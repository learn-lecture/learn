package org.delivery.api.domain.user.controller.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(
	@NotBlank
	String name,

	@NotBlank
	@Email
	String email,

	@NotBlank
	String address,

	@NotBlank
	String password
) {
}
