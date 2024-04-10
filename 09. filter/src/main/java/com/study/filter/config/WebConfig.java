package com.study.filter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.study.filter.utility.OpenApiInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final OpenApiInterceptor openApiInterceptor;

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
	//	WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(openApiInterceptor)
			.addPathPatterns("/*");
	}
}
