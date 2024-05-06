package org.delivery.api.account;

import org.delivery.api.account.model.AccountResponse;
import org.delivery.db.account.AccountEntity;
import org.delivery.db.account.AccountRepository;
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
	public AccountResponse test() {
		return new AccountResponse("DOT", 25, "male");
	}

}
