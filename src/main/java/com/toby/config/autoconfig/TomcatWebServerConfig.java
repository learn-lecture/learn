package com.toby.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;

import com.toby.config.MyAutoConfiguration;
import org.springframework.core.type.AnnotatedTypeMetadata;

@MyAutoConfiguration
@Conditional(TomcatWebServerConfig.TomcatCondition.class)
public class TomcatWebServerConfig {

	@Bean(name = "tomcatWebServetFactory")
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	static class TomcatCondition implements Condition {
		@Override
		public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
			return false;
		}
	}

}
