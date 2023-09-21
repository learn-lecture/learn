package com.example.restapi;

import com.example.restapi.Model.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApiApplicationTests {

	// Spring에서 관리하는 Bean들 중에 자동응로 생성되는 ObjectMapper를 가져옴
	// DI 직접 객체를 생성하지 않고 spring에서 직접 의존성을 주입시켜줌.
	@Autowired
	private ObjectMapper objectMapper;

	// ObjectMapper는 기본적으로 내장되어 자동으로 매핑되어 있음.
	@Test
	void contextLoads() throws JsonProcessingException {
		var user = new UserRequest();
		user.setUserName("홍길동");
		user.setUserAge(10);
		user.setEmail("hong@gmail.com");
		user.setIsKorean(true);

		// 직렬화
		var json = objectMapper.writeValueAsString(user);
		System.out.println(json);

		// 역직렬화
		var dto = objectMapper.readValue(json, UserRequest.class);
		System.out.println(dto);
	}

}
