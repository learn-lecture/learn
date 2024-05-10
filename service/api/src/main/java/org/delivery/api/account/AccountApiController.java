package org.delivery.api.account;

import org.delivery.api.account.exception.AccountExceptionType;
import org.delivery.api.account.model.AccountResponse;
import org.delivery.api.account.model.resultinfo.AccountResultType;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.api.Result;
import org.delivery.api.exception.model.NotFoundException;
import org.delivery.db.account.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountApiController {

	private final AccountRepository accountRepository;

	@GetMapping
	public ResponseEntity<Api<AccountResponse>> test() {
		//final Result result = Result.of(AccountResultType.ACCOUNT_RESULT_SUCCESS);
		final Result result = Result.of(AccountResultType.ACCOUNT_RESULT_SUCCESS);
		final AccountResponse response = new AccountResponse("DOT", 25, "male");
		final Api<AccountResponse> body = Api.ok(result, response);

		return ResponseEntity.ok(body);
	}

	@GetMapping("/error")
	public ResponseEntity<Api<AccountResponse>> error() {
		throw new NotFoundException(AccountExceptionType.INVALID_ERROR);
	}

}
