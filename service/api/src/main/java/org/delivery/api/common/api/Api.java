package org.delivery.api.common.api;

import jakarta.validation.Valid;

public record Api<T>(
	Result result,
	@Valid
	T body
) {

	public static <T> Api<T> ok(final Result result, final T body) {
		return new Api<>(result, body);
	}

}