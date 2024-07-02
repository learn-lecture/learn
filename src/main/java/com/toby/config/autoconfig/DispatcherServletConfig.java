package com.toby.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.toby.config.MyAutoConfiguration;

@MyAutoConfiguration
public class DispatcherServletConfig {

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

}
