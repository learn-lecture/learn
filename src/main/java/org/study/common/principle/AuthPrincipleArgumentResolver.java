package org.study.common.principle;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.study.auth.domain.TokenProvider;

public class AuthPrincipleArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;

    public AuthPrincipleArgumentResolver(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthPrinciple.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        try {
            String authToken = webRequest.getHeader("Authorization");
            String token = extractToken(authToken);
            return new UserPrinciple(tokenProvider.getUserId(token), tokenProvider.getUserRole(token));
        } catch (Exception e) {
            throw new IllegalArgumentException("올바르지 않은 토큰입니다.");
        }
    }

    private static String extractToken(String authToken) {
        String[] parseToken = authToken.split(" ");
        validateTokenParsing(authToken, parseToken);

        return parseToken[1];
    }

    private static void validateTokenParsing(String authToken, String[] parseToken) {
        if (authToken == null || parseToken.length != 2) {
            throw new IllegalArgumentException("토큰 정보가 없습니다.");
        }
    }
}
