package org.delivery.api.exception.model;

import org.delivery.api.common.api.ResultType;
import org.delivery.api.exception.BaseException;

public class NotFoundException extends BaseException {

	public NotFoundException(final ResultType exceptionType) {
		super(exceptionType);
	}

}
