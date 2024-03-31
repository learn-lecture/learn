package com.toby.spring;

import org.springframework.boot.SpringApplication;

import com.toby.spring.utility.MySpringBootAnnotation;

@MySpringBootAnnotation
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
