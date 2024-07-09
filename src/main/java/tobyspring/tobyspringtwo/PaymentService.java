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
		// todo 환율가져오기
		// https://open.er-api.com/v6/latest/USD
		final URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
		final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		final String response = br.lines().collect(Collectors.joining());
		br.close();

		final ObjectMapper mapper = new ObjectMapper();
		final ExRateData data = mapper.readValue(response, ExRateData.class);
		final BigDecimal exRate = data.rates().get("KRW");
		System.out.println(exRate);

		// todo 금액 계산

		// todo 유효 시간 계산
		return Payment.builder()
			.orderId(orderId)
			.currency(currency)
			.foreignCurrencyAmount(foreignCurrencyAmount)
			.exRate(BigDecimal.ZERO)
			.convertedAmount(BigDecimal.ZERO)
			.validUntil(LocalDateTime.now())
			.build();
	}

	public static void main(String[] args) throws IOException {
		final PaymentService paymentService = new PaymentService();
		final Payment prepare = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(prepare);
	}

}
