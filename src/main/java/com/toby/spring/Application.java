package com.toby.spring;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.toby.spring.contoller.HelloController;
import com.toby.spring.service.SimpleHelloService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Application {

	public static void main(String[] args) {
		// Spring Container
		// resource, event, ...
		final GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
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
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh();

	}

}
