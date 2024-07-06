package com.toby.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;


public class ServerProperties {

	private String path;
	private int port;

	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

	public int getPort() {
		return port;
	}

	public void setPort(final int port) {
		this.port = port;
	}
}
