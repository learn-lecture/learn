package org.delivery.api.account.model.resultInfo;

import lombok.Getter;

@Getter
public enum AccountResultType {

	ACCOUNT_RESULT_SUCCESS(200, "OK", "성공");

	private final int code;
	private final String message;
	private final String description;

	AccountResultType(final int code, final String message, final String description) {
		this.code = code;
		this.message = message;
		this.description = description;
	}

}
