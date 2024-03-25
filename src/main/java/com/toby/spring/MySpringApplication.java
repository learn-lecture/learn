package com.toby.spring;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
	public static void run(final Class<?> applicationClass, final String... args) {
		// Spring Container
		// resource, event, ...
		final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				// Bean 추출 및 서블렛 세팅
				final ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				final DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

				final WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*");
				});
				webServer.start();
				// ----- Success WebServer Start -----
			}
		};
		// bean 등록 후 초기화하기
		applicationContext.register(applicationClass);
		applicationContext.refresh();
	}

}
