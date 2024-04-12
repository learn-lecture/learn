package com.study.cookie.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.cookie.model.LoginRequest;
import com.study.cookie.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountApiController {

	private final UserService userService;

	@PostMapping("/login")
	public void login(
		@RequestBody
		final LoginRequest loginRequest,
		final HttpServletResponse httpServletResponse
	){
		userService.login(loginRequest, httpServletResponse);
	}
}