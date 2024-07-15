package tobyspring.tobyspringtwo.exrate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.tobyspringtwo.api.ApiExecutor;
import tobyspring.tobyspringtwo.api.ErApiExRateExtractor;
import tobyspring.tobyspringtwo.api.ExRateExtractor;
import tobyspring.tobyspringtwo.api.SimpleApiExecutor;
import tobyspring.tobyspringtwo.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

	@Override
	public BigDecimal getExRate(final String currency) {
		final String url = "https://open.er-api.com/v6/latest/" + currency;

		return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
	}

	private static BigDecimal runApiForExRate(
			final String url,
			final ApiExecutor apiExecutor,
			final ExRateExtractor exRateExtractor
	) {
		final URI uri;

		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		final HttpURLConnection connection;
		final String response;

		try {
			response = apiExecutor.execute(uri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			return exRateExtractor.extract(response);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
