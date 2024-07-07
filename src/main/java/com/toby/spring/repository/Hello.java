package com.toby.spring.repository;

public class Hello {

	private String hello;
	private int count;

	public Hello(final String hello, final int count) {
		this.hello = hello;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public String getHello() {
		return hello;
	}

}
