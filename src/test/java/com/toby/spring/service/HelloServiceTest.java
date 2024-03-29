package com.toby.spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloServiceTest {

	@Test
	void simpleHelloService() {
		final SimpleHelloService helloService = new SimpleHelloService();
		final String res = helloService.sayHello("test");

		Assertions.assertThat(res).isEqualTo("Hello test");

	}

}