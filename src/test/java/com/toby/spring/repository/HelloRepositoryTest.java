package com.toby.spring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {

	@Autowired
	HelloRepository helloRepository;

	@Test
	void findHelloFailed() {
		Assertions.assertThat(helloRepository.findHello("test")).isNull();
	}

	@Test
	void increaseCount() {
		Assertions.assertThat(helloRepository.countOf("test")).isEqualTo(0);

		helloRepository.increaseCount("test");
		Assertions.assertThat(helloRepository.countOf("test")).isEqualTo(1);

		helloRepository.increaseCount("test");
		Assertions.assertThat(helloRepository.countOf("test")).isEqualTo(2);
	}

}
