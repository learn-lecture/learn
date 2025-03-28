package org.study.common.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.study.auth.domain.TokenProvider;
import org.study.common.principle.AuthPrincipleArgumentResolver;

@Configuration
@RequiredArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

    private final TokenProvider tokenProvider;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthPrincipleArgumentResolver(tokenProvider));
    }

}
