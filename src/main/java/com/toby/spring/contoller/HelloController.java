package com.toby.spring.contoller;

import java.util.Objects;

import com.toby.spring.service.HelloService;
import com.toby.spring.service.SimpleHelloService;

public class HelloController {

	private final HelloService helloService;

	public HelloController(final HelloService helloService) {
		this.helloService = helloService;
	}

	public String hello(final String name) {
		return helloService.sayHello(Objects.requireNonNull(name));
	}

}
