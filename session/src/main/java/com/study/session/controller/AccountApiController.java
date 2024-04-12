package com.study.session.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.session.model.LoginRequest;
import com.study.session.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountApiController {

	private final UserService service;

	@PostMapping("/login")
	public void login(
		@RequestBody
		final LoginRequest request,
		final HttpSession session
	) {
		service.login(request, session);
	}

}
