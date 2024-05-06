package org.delivery.api.account;

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
	public void save() {
		final AccountEntity entity = AccountEntity.builder().build();
		accountRepository.save(entity);
	}

}
