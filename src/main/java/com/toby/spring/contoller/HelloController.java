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
	private final ApplicationContext applicationContext;

	public HelloController(
		final HelloService helloService,
		final ApplicationContext applicationContext
	) {
		this.helloService = helloService;
		this.applicationContext = applicationContext;

		System.out.println(applicationContext);
	}

	@GetMapping("/hello")
	public String hello(final String name) {
		return helloService.sayHello(Objects.requireNonNull(name));
	}

}
