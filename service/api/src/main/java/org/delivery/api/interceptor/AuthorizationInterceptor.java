package org.delivery.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.domain.token.controller.business.TokenBusiness;
import org.delivery.api.domain.user.exception.UserExceptionType;
import org.delivery.common.exception.model.NotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

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
        //final String accessToken = generatedAccessToken(request);
        final String userId = request.getHeader("x-user-id");
        if (userId == null) {
            throw new NotFoundException(UserExceptionType.NOT_FOUND_EXCEPTION);
        }

        //final Long userId = tokenBusiness.validationToken(accessToken);
        final RequestAttributes context = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        context.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);

        return true;
    }

    private static String generatedAccessToken(final HttpServletRequest request) {
        final String accessToken = request.getHeader("authorization");
        if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer ")) {
            return accessToken.split(" ")[1];
        }
        return null;
    }

}
