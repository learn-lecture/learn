package com.toby.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.toby.config.MyAutoConfiguration;

@MyAutoConfiguration
public class ServerPropertiesConfig {

	@Bean
	public ServerProperties serverProperties(final Environment environment) {
		final ServerProperties serverProperties = new ServerProperties();

		serverProperties.setPath(environment.getProperty("contextPath"));
		serverProperties.setPort(Integer.parseInt(environment.getProperty("port")));

		return serverProperties;
	}

}
