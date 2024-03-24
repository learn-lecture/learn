package com.toby.spring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class Application {

	public static void main(String[] args) {
		// Spring Container
		// resource, event, ...
		final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				// Tomcat 외 다른 서버도 구현할 수 있도록 interface로 명시되어 있음.
				final ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				final WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet(
						"frontcontroller",
						new DispatcherServlet(this)
					).addMapping("/*");
				});
				webServer.start();
				// ----- Success WebServer Start -----
			}
		};
		// bean 등록 후 초기화하기
		applicationContext.register(Application.class);
		applicationContext.refresh();

	}

}
