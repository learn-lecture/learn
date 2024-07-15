package tobyspring.tobyspringtwo.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import lombok.RequiredArgsConstructor;
import tobyspring.tobyspringtwo.api.*;
import tobyspring.tobyspringtwo.payment.ExRateProvider;

@RequiredArgsConstructor
public class WebApiExRateProvider implements ExRateProvider {

	private final ApiTemplate apiTemplate;

	@Override
	public BigDecimal getExRate(final String currency) {
		final String url = "https://open.er-api.com/v6/latest/" + currency;

		return apiTemplate.getExRate(url);
 	}

}
