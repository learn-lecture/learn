package org.delivery.api.interceptor;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final Object handler
	) throws Exception {
		log.info("Authorization Interceptor url : {}", request.getRequestURI());

		if (HttpMethod.OPTIONS.matches(request.getMethod())) {
			return true;
		}

		if (handler instanceof ResourceHttpRequestHandler) {
			return true;
		}


		// TODO handler 검증
		return false;
	}

}
