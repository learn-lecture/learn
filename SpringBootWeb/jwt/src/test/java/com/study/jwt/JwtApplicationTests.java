package com.study.jwt;

import java.util.HashMap;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.jwt.service.JwtService;

@SpringBootTest
class JwtApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
	}

	@Test
	void tokenCreate() {
		final HashMap<String, Object> claims = new HashMap<>();
		final int expiredAt = 30000;

		claims.put("user_id", 100);

		final String s = jwtService.create(claims, expiredAt);

		System.out.println(s);
	}

	@Test
	void tokenValidation() {
		final String token =
			"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMDAsImV4cCI6MTcxMzAxMjQ2MywiaWF0IjoxNzEzMDEyNDMzfQ.-qWKWcT2lFOLZa5LTdWCi36DrIMke-EW2PjPW39HjpA";
		jwtService.validation(token);
	}
}
