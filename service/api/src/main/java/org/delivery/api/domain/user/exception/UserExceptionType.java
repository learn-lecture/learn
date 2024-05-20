package org.delivery.api.domain.user.exception;

import org.delivery.api.common.api.ResultType;

import lombok.Getter;

@Getter
public enum UserExceptionType implements ResultType {

	NOT_FOUND_EXCEPTION(1008, "사용자 정보를 찾지 못했습니다.");

	private final int code;
	private final String message;

	UserExceptionType(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

}
