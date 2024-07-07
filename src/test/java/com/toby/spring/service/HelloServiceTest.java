package com.toby.spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.toby.spring.repository.Hello;
import com.toby.spring.repository.HelloRepository;

class HelloServiceTest {

	@Test
	void simpleHelloService() {
		final SimpleHelloService helloService = new SimpleHelloService(helloRepositoryStub);
		final String res = helloService.sayHello("test");

		Assertions.assertThat(res).isEqualTo("Hello test");

	}

	private static HelloRepository helloRepositoryStub = new HelloRepository() {
		@Override
		public Hello findHello(final String name) {
			return null;
		}

		@Override
		public void increaseCount(final String name) {

		}
	};

	@Test
	void helloDecorator() {
		final HelloDecorator decorator = new HelloDecorator(name -> name);
		final String test = decorator.sayHello("test");

		Assertions.assertThat(test).isEqualTo("*test*");
	}

}