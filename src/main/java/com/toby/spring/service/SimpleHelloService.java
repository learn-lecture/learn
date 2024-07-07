package com.toby.spring.service;

import org.springframework.stereotype.Component;

import com.toby.spring.repository.HelloRepository;
import com.toby.spring.utility.MyComponent;

@MyComponent
public class SimpleHelloService implements HelloService {

	private final HelloRepository helloRepository;

	public SimpleHelloService(HelloRepository helloRepository) {
		this.helloRepository = helloRepository;
	}

	@Override
	public String sayHello(final String name) {
		this.helloRepository.increaseCount(name);
		return "Hello " + name;
	}

	@Override
	public int countOf(final String name) {
		return helloRepository.countOf(name);
	}

}
