package org.delivery.api.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggerFilter implements Filter {

	private static final String REQUEST_DIVIDE = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	private static final String RESPONSE_DIVIDE = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

	@Override
	public void doFilter(
		final ServletRequest servletRequest,
		final ServletResponse servletResponse,
		final FilterChain filterChain
	) throws IOException, ServletException {
		final var request = new ContentCachingRequestWrapper((HttpServletRequest)servletRequest);
		final var response = new ContentCachingResponseWrapper((HttpServletResponse)servletResponse);

		filterChain.doFilter(request, response);

		final String uri = request.getRequestURI();
		final String method = request.getMethod();
		final String ip = request.getRemoteAddr();

		printRequestLog(uri, method, ip, request);
		printResponseLog(uri, method, ip, response);
		response.copyBodyToResponse();
	}

	private void printRequestLog(
		final String uri,
		final String method,
		final String ip,
		final ContentCachingRequestWrapper request
	) {
		final String requestBody = new String(request.getContentAsByteArray());
		final String requestHeaders = headerInfos(request);
		log.info("\n{}\nuri: {}\nip: {}\nmethod: {}\nheader: {}\nbody: {}\n{}",
			REQUEST_DIVIDE, uri, ip, method, requestHeaders, requestBody, REQUEST_DIVIDE);
	}

	private void printResponseLog(
		final String uri,
		final String method,
		final String ip,
		final ContentCachingResponseWrapper response
	) {
		final String responseBody = new String(response.getContentAsByteArray());
		final String responseHeaders = headerInfos(response);
		log.info("\n{}\nuri: {}\nip: {}\nmethod: {}\nheader: {}\nbody: {}\n{}",
			RESPONSE_DIVIDE, uri, ip, method, responseHeaders, responseBody, RESPONSE_DIVIDE);
	}

	private String headerInfos(final ContentCachingRequestWrapper request) {
		final Enumeration<String> headerNames = request.getHeaderNames();
		final StringBuilder headerValues = new StringBuilder();

		headerNames.asIterator().forEachRemaining(key -> {
			headerValues.append("[")
				.append(key)
				.append(": ")
				.append(request.getHeader(key))
				.append("] ");
		});

		return headerValues.toString();
	}

	private String headerInfos(final ContentCachingResponseWrapper response) {
		final Collection<String> headerNames = response.getHeaderNames();
		final StringBuilder headerValues = new StringBuilder();

		headerNames.forEach(key -> {
			headerValues.append("[")
				.append(key)
				.append(": ")
				.append(response.getHeader(key))
				.append("] ");
		});

		return headerValues.toString();
	}
}
