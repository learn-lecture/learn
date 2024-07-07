package com.toby.spring.contoller;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toby.spring.service.HelloService;

@RestController
public class HelloController {

	private final HelloService helloService;

	public HelloController(final HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/hello")
	public String hello(final String name) {
		if (Objects.isNull(name) || name.trim().isBlank()) {
			throw new IllegalArgumentException();
		}

		return helloService.sayHello(name);
	}

	@GetMapping("/count")
	public String count(String name) {
		return name + ": " + helloService.countOf(name);
	}

}
