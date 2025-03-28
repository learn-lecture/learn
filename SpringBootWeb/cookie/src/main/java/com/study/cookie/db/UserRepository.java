package com.study.cookie.db;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.study.cookie.model.UserDto;

import jakarta.annotation.PostConstruct;

@Service
public class UserRepository {

	private final List<UserDto> userList = new ArrayList<>();

	public Optional<UserDto> findById(final String id) {
		return userList
			.stream()
			.filter(it -> it.id().equals(id))
			.findFirst();
	}

	public Optional<UserDto> findByName(final String name) {
		return userList
			.stream()
			.filter(it -> it.name().equals(name))
			.findFirst();
	}

	@PostConstruct
	public void start() {
		userList.add(
			new UserDto(
				UUID.randomUUID().toString(),
				"김지환",
				"1234"
			)
		);

		userList.add(
			new UserDto(
				UUID.randomUUID().toString(),
				"jihwan",
				"1234"
			)
		);

		userList.add(
			new UserDto(
				UUID.randomUUID().toString(),
				"kimjihwan",
				"1234"
			)
		);
	}
}