package org.delivery.api.account.model.resultinfo;

import org.delivery.api.common.api.ResultType;

import lombok.Getter;

@Getter
public enum AccountResultType implements ResultType {

	ACCOUNT_RESULT_SUCCESS(200, "성공");

	private final int code;
	private final String message;

	AccountResultType(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

}
