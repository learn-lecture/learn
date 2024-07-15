package tobyspring.tobyspringtwo.exrate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.tobyspringtwo.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

	@Override
	public BigDecimal getExRate(final String currency) {
		final String url = "https://open.er-api.com/v6/latest/" + currency;
        final URI uri;

        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        final HttpURLConnection connection;
		final String response;

        try {
            connection = (HttpURLConnection) uri.toURL().openConnection();
			try (final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				response = br.lines().collect(Collectors.joining());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

        try {
			final ObjectMapper mapper = new ObjectMapper();
			final ExRateData data = mapper.readValue(response, ExRateData.class);
			return data.rates().get("KRW");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
	}

}
