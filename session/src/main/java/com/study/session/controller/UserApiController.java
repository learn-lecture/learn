package com.study.session.controller;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.session.model.UserDto;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserApiController {

	@GetMapping("/me")
	public UserDto me(final HttpSession session) {
		final Object user = session.getAttribute("USER");
		if (Objects.isNull(user)) {
			return null;
		}

		return (UserDto)user;
	}

}
