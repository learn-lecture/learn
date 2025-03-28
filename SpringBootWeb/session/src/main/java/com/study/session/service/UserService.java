package com.study.session.service;

import org.springframework.stereotype.Service;

import com.study.session.db.UserRepository;
import com.study.session.model.LoginRequest;
import com.study.session.model.UserDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;

	public void login(
		final LoginRequest request,
		final HttpSession session
	) {
		final String id = request.id();
		final String password = request.password();

		final UserDto user = repository.findByName(id).orElseThrow(() -> {
			throw new IllegalArgumentException("Not Found");
		});

		if (!user.password().equals(password)) {
			throw new IllegalArgumentException("Wrong Password");
		}

		session.setAttribute("USER", user);
	}

}
