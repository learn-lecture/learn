package org.delivery.api.exception.model.info;

import org.delivery.api.common.api.ResultType;

import lombok.Getter;

@Getter
public enum ExceptionType implements ResultType {

	INTERNAL_SERVER_ERROR(1001, "시스템 오류입니다."),
	INVALID_VALUE_ERROR(1002, "요청 JSON 데이터 오류"),
	DATABASE_CONSTRAINTS_ERROR(1003, "DB 오류 입니다."),
	TYPE_MISS_ERROR(1004, "요청 데이터 오류입니다. (데이터 형식이 다릅니다. (숫자, 문자, 범위 등)"),
	INVALID_PARAMETER_ERROR(1005, "필수 파라미터가 없습니다.");

	private final int code;
	private final String message;

	ExceptionType(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

}
