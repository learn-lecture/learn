package org.delivery.api.domain.user.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.delivery.api.domain.user.exception.UserExceptionType;
import org.delivery.api.exception.model.NotFoundException;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.vo.UserStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;

	@Transactional
	public UserEntity register(final UserEntity entity) {
		return Optional.ofNullable(entity)
			.map(it -> {
				entity.setStatus(UserStatus.REGISTERED);
				entity.setRegisteredAt(LocalDateTime.now());
				return repository.save(entity);
			}).orElseThrow();
	}

	public UserEntity login(final String email, final String password) {
		final UserEntity user = getUser(email, password);
		return user;
	}

	private UserEntity getUser(final String email, final String password) {
		return repository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
			email,
			password,
			UserStatus.REGISTERED
		).orElseThrow(() -> new NotFoundException(UserExceptionType.NOT_FOUND_EXCEPTION));
	}

}
