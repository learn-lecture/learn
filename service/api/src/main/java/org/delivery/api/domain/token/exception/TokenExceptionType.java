package org.delivery.api.domain.token.exception;

import org.delivery.api.common.api.ResultType;

import lombok.Getter;

@Getter
public enum TokenExceptionType implements ResultType {

	ACCESS_TOKEN_NULL_EXCEPTION(1100, "액세스 토큰 정보는 NULL 일 수 없습니다."),
	REFRESH_TOKEN_NULL_EXCEPTION(1101, "리프레시 토큰 정보는 NULL 일 수 없습니다."),
	INVALID_TOKEN_EXCEPTION(1102, "유효하지 않은 토큰 정보입니다."),
	NOTFOUND_TOKEN_EXCEPTION(1103, "토큰 헤더를 찾을 수 없습니다.");

	private final int code;
	private final String message;

	TokenExceptionType(final int code, final String message) {
		this.code = code;
		this.message = message;
	}


}
