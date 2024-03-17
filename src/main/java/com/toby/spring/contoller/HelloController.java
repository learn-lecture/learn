package com.toby.spring.contoller;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toby.spring.service.HelloService;

@Controller
@RequestMapping
public class HelloController {

	private final HelloService helloService;

	public HelloController(final HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello(final String name) {
		return helloService.sayHello(Objects.requireNonNull(name));
	}

}
