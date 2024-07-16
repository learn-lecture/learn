package org.delivery.api.resolver;

import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {

	private final UserService userService;

	@Override
	public boolean supportsParameter(final MethodParameter parameter) {
		return parameter.withContainingClass(User.class).hasParameterAnnotation(UserSession.class);
	}

	@Override
	public Object resolveArgument(
		final MethodParameter parameter,
		final ModelAndViewContainer mavContainer,
		final NativeWebRequest webRequest,
		final WebDataBinderFactory binderFactory
	) throws Exception {
		final RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
		final Object userId = attrs.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

		return userService.getUserWithThrow(Long.parseLong(userId.toString()));
	}

}
