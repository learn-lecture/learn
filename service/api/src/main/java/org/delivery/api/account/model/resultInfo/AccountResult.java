package org.delivery.api.account.model.resultInfo;

import org.delivery.api.common.api.Result;

public class AccountResult extends Result {

	public AccountResult(final AccountResultType resultType) {
		super(resultType.getCode(), resultType.getMessage(), resultType.getDescription());
	}

}
