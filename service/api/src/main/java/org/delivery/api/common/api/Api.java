package org.delivery.api.common.api;

import jakarta.validation.Valid;

public record Api<T>(
	Result result,
	@Valid
	T body
) {
}