package com.toby.spring.contoller;

import java.util.Objects;

import com.toby.spring.service.SimpleHelloService;

public class HelloController {

	final SimpleHelloService simpleHelloService = new SimpleHelloService();

	public String hello(final String name) {
		return simpleHelloService.sayHello(Objects.requireNonNull(name));
	}

}
