package org.delivery.api.exception;

import org.delivery.api.common.api.ResultType;

public class BadRequestException extends BaseException {

	public BadRequestException(final ResultType exceptionType) {
		super(exceptionType);
	}

}
