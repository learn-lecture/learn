package com.toby.spring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.toby.spring.config.HelloBootTest;

@HelloBootTest
public class HelloRepositoryTest {

	@Autowired
	HelloRepository helloRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	void init() {
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}


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
