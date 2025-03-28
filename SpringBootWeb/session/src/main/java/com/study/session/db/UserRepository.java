package com.study.session.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.study.session.model.UserDto;

import jakarta.annotation.PostConstruct;

@Service
public class UserRepository {

	private List<UserDto> users = new ArrayList<>();

	public Optional<UserDto> findByName(final String name) {
		return users.stream()
			.filter(it -> it.name().equals(name))
			.findFirst();
	}

	@PostConstruct
	public void init() {

		users.add(new UserDto("김지환", "1234"));
		users.add(new UserDto("DOT", "1234"));
		users.add(new UserDto("JIHWAN", "1234"));

	}
}
