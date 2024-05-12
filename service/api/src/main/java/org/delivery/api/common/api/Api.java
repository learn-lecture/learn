package org.delivery.api.common.api;

import jakarta.validation.Valid;

public record Api<T>(
	Result result,
	@Valid
	T body
) {

	public static <T> Api<T> ok(final ResultType resultType, final T body) {
		final Result result = Result.of(resultType);
		return new Api<>(result, body);
	}

}