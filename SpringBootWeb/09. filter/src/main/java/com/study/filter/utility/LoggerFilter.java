package com.study.filter.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class LoggerFilter implements Filter {
	@Override
	public void doFilter(
		final ServletRequest servletRequest,
		final ServletResponse servletResponse,
		final FilterChain filterChain
	) throws IOException, ServletException {
		log.info(">>>> 진입 ");

		/*
			아래와 같이 Dto에 대해 log를 찍어볼 수 있다.
			하지만, InputStream에서 값을 읽어오는데 Controller에서
			이 정보에 대해 읽기 전에 getReader()를 통해서 읽어오는 문제 때문에
			Controller에서는 Dto에 대한 정보를 읽을 수가 없어진다.
		*/
		/*
		final HttpServletRequestWrapper req
			= new HttpServletRequestWrapper((HttpServletRequest)servletRequest);
		final HttpServletResponseWrapper res
			= new HttpServletResponseWrapper((HttpServletResponse)servletResponse);

		final BufferedReader reader = req.getReader();
		final List<String> list = reader.lines().toList();

		list.forEach(it -> {log.info(it);});
		*/

		final ContentCachingRequestWrapper req
			= new ContentCachingRequestWrapper((HttpServletRequest)servletRequest);
		final ContentCachingResponseWrapper res
			= new ContentCachingResponseWrapper((HttpServletResponse)servletResponse);

		filterChain.doFilter(req, res);

		final String reqJson = new String(req.getContentAsByteArray());
		log.info("req : {}", reqJson);

		final String resJson = new String(res.getContentAsByteArray());
		log.info("res : {}", resJson);

		log.info("<<<< 리턴");

		res.copyBodyToResponse();
	}
}
