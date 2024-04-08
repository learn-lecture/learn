package com.study.filter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.filter.request.UserRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserApiController {

	@PostMapping("")
	public UserRequest register(
		@RequestBody
		final UserRequest request
	) {
		log.info("{}", request);
		return request;
	}

}
