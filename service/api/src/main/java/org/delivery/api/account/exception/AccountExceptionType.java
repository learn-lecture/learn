package org.delivery.api.account.exception;

import org.delivery.api.common.api.ResultType;

import lombok.Getter;

@Getter
public enum AccountExceptionType implements ResultType {

	INVALID_ERROR(1006, "유효하지 않은 입력입니다.");

	private final int code;
	private final String message;

	AccountExceptionType(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

}
