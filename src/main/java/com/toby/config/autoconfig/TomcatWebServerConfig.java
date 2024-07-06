package com.toby.config.autoconfig;

import com.toby.config.ConditionalMyOnClass;
import com.toby.config.MyAutoConfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@Import(ServerProperties.class)
public class TomcatWebServerConfig {

    @Bean(name = "tomcatWebServetFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(final ServerProperties properties) {
        final TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();

        tomcat.setContextPath(properties.getContextPath());
        tomcat.setPort(properties.getPort());

        return tomcat;
    }

}
