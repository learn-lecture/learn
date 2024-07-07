package com.toby.spring.service;

import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toby.spring.config.HelloBootTest;
import com.toby.spring.repository.HelloRepository;

@HelloBootTest
public class HelloServiceCountTest {

	@Autowired HelloService helloService;
	@Autowired HelloRepository helloRepository;

	@Test
	void sayHelloIncreaseCount() {
		IntStream.rangeClosed(1, 10).forEach(count -> {
			helloService.sayHello("test");
			Assertions.assertThat(helloRepository.countOf("test")).isEqualTo(count);
		});
	}

}
