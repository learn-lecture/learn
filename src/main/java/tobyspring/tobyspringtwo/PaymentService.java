package tobyspring.tobyspringtwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentService {

	public Payment prepare(
		final Long orderId,
		final String currency,
		final BigDecimal foreignCurrencyAmount
	) throws IOException {
		final BigDecimal exRate = getExRate(currency);
		final BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		final LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);

		return Payment.builder()
			.orderId(orderId)
			.currency(currency)
			.foreignCurrencyAmount(foreignCurrencyAmount)
			.exRate(exRate)
			.convertedAmount(convertedAmount)
			.validUntil(localDateTime)
			.build();
	}

	private static BigDecimal getExRate(final String currency) throws IOException {
		final URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
		final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		final String response = br.lines().collect(Collectors.joining());
		br.close();

		final ObjectMapper mapper = new ObjectMapper();
		final ExRateData data = mapper.readValue(response, ExRateData.class);
		final BigDecimal exRate = data.rates().get("KRW");
		return exRate;
	}

	public static void main(String[] args) throws IOException {
		final PaymentService paymentService = new PaymentService();
		final Payment prepare = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(prepare);
	}

}
