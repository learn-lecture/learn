package org.delivery.api.common.api;

public record Result(
	Integer resultCode,
	String resultMessage
) {

	public static Result of(final ResultType resultType) {
		return new Result(resultType.getCode(), resultType.getMessage());
	}

}
