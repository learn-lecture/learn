package com.toby.spring.contoller;

import java.util.Objects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		return helloService.sayHello(Objects.requireNonNull(name));
	}

}
