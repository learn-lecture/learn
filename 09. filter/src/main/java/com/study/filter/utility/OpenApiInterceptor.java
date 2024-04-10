package com.study.filter.utility;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OpenApiInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final Object handler
	) throws Exception {
		// true Controller로 전달, fasle Controller로 전달하지 않음.
		log.info("pre handle");

		final HandlerMethod handlerMethod = (HandlerMethod)handler;
		final OpenApi methodLevel = handlerMethod.getMethodAnnotation(OpenApi.class);
		if (methodLevel != null) {
			log.info("method Level");
			return true;
		}

		final OpenApi classLevel = handlerMethod.getBeanType().getAnnotation(OpenApi.class);
		if (classLevel != null) {
			log.info("class Level");
			return true;
		}

		final GetMapping getMethod = handlerMethod.getMethodAnnotation(GetMapping.class);
		if(getMethod != null) {
			log.info("getMapping test");
			return true;
		}

		log.info("not openApi");
		return false;
	}

	@Override
	public void postHandle(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final Object handler,
		final ModelAndView modelAndView
	) throws Exception {
		log.info("post handle");
		//HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final Object handler,
		final Exception ex
	) throws Exception {
		log.info("after completion");
		//HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
