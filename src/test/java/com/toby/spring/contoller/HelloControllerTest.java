package com.toby.spring.contoller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloControllerTest {

	@Test
	void helloController() {
		final HelloController helloController = new HelloController(name -> name);

		final String res = helloController.hello("test");

		Assertions.assertThat(res).isEqualTo("test");
	}

	@Test
	void failsHelloController() {
		final HelloController helloController = new HelloController(name -> name);

		Assertions.assertThatThrownBy(()->{
			helloController.hello(null);
		}).isInstanceOf(IllegalArgumentException.class);

		Assertions.assertThatThrownBy(()->{
			helloController.hello("   ");
		}).isInstanceOf(IllegalArgumentException.class);
	}

}