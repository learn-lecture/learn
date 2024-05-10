package org.delivery.api.exception;

import org.delivery.api.common.api.ResultType;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

	private final ResultType exceptionType;

	public BaseException(final ResultType exceptionType) {
		super(exceptionType.getMessage());
		this.exceptionType = exceptionType;
	}

}
