package com.toby.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class Application {

	public static void main(String[] args) {
		// Tomcat 외 다른 서버도 구현할 수 있도록 interface로 명시되어 있음.
		final ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		final WebServer webServer = serverFactory.getWebServer();
		webServer.start();
		// ----- Success WebServer Start -----
	}

}
