package com.toby.spring;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {

	@Test
	void helloApi() {
		final TestRestTemplate rest = new TestRestTemplate();

		final ResponseEntity<String> res =
			rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "param");

		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
		assertThat(res.getBody()).isEqualTo("*Hello param*");
	}

	@Test
	void failsHelloApi() {
		final TestRestTemplate rest = new TestRestTemplate();

		final ResponseEntity<String> res =
			rest.getForEntity("http://localhost:8080/hello?name=", String.class);

		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
