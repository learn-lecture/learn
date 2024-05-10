package org.delivery.api.exception.model;

import org.delivery.api.common.api.ResultType;

public record ExceptionResponse(
	Integer errorCode,
	String errorMessage
) {

	public static ExceptionResponse from(final ResultType errorType) {
		return new ExceptionResponse(errorType.getCode(), errorType.getMessage());
	}

}
