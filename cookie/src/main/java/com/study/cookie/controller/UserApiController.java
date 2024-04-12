package com.study.cookie.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.cookie.db.UserRepository;
import com.study.cookie.model.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserApiController {

	private final UserRepository repository;

	@GetMapping("/me")
	public UserDto me(
		final HttpServletRequest request,
		@CookieValue(name = "authorization-cookie", required = false)
		String authorizationCookie
	) {
		log.info(authorizationCookie);

		final UserDto user = repository.findById(authorizationCookie).orElseThrow(() -> {
			throw new IllegalArgumentException("Not Found");
		});

		return user;
		/*
		final Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				log.info("key : {}, value : {}", cookie.getName(), cookie.getValue());
			}
		}

		return null;
	*/
	}

}
