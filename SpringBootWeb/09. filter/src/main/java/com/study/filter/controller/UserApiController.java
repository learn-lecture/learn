package com.study.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.filter.request.UserRequest;
import com.study.filter.utility.OpenApi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserApiController {

	@OpenApi
	@PostMapping("")
	public UserRequest register(
		@RequestBody
		final UserRequest request
	) {
		log.info("{}", request);
		return request;
	}

	@GetMapping
	public String hello() {
		log.info("hello");
		return "hello";
	}

}
