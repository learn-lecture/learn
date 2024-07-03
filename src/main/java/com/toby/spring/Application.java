package com.toby.spring;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.toby.config.MySpringBootApplication;

@MySpringBootApplication
public class Application {

	@Bean
	ApplicationRunner applicationRunner(final Environment environment) {
		return args -> {
			final String name = environment.getProperty("my.name");
			System.out.println("my.naem : " + name);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
