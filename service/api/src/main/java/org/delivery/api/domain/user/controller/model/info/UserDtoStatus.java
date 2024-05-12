package org.delivery.api.domain.user.controller.model.info;

import org.delivery.api.common.api.ResultType;

import lombok.Getter;

@Getter
public enum UserDtoStatus implements ResultType {

	USER_REGISTERED_SUCCESS(200, "사용자 회원가입이 성공했습니다.");

	private final int code;
	private final String message;

	UserDtoStatus(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

}
