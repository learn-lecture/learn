package com.toby.spring.service;

import org.springframework.stereotype.Component;

import com.toby.spring.utility.MyComponent;

@MyComponent
public class SimpleHelloService implements HelloService {

	@Override
	public String sayHello(final String name) {
		return "Hello " + name;
	}

}
