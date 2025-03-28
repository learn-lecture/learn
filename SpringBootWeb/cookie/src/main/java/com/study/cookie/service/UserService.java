package com.study.cookie.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.study.cookie.db.UserRepository;
import com.study.cookie.model.LoginRequest;
import com.study.cookie.model.UserDto;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	// login logic
	public String login(
		final LoginRequest loginRequest,
		final HttpServletResponse httpServletResponse
	) {
		final String id = loginRequest.id();
		final String pw = loginRequest.password();

		final UserDto user = userRepository.findByName(id).orElseThrow(() -> {
			throw new IllegalArgumentException("Not Found");
		});

		if (user.password().equals(pw)) {
			/*final Cookie cookie = new Cookie("authorization-cookie", user.id());
			cookie.setDomain("localhost");
			cookie.setPath("/");
			cookie.setHttpOnly(true);
			//cookie.setSecure(true); // https 에서만 사용되도록 설정
			cookie.setMaxAge(-1);

			httpServletResponse.addCookie(cookie);*/
			return user.id();
		}
		return id;
	}

}