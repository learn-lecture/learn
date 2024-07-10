package tobyspring.tobyspringtwo;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

	private final WebApiExRateProvider webApiExrateProvider;

	public PaymentService() {
		this.webApiExrateProvider = new WebApiExRateProvider();
	}

	public Payment prepare(
		final Long orderId,
		final String currency,
		final BigDecimal foreignCurrencyAmount
	) throws IOException {
		final BigDecimal exRate = webApiExrateProvider.getWebExRate(currency);
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

}
