package tobyspring.tobyspringtwo.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class PaymentService {

	private final ExRateProvider exRateProvider;
	private final Clock clock;

	public PaymentService(final ExRateProvider exRateProvider, final Clock clock) {
		this.exRateProvider = exRateProvider;
		this.clock = clock;
	}

	public Payment prepare(
		final Long orderId,
		final String currency,
		final BigDecimal foreignCurrencyAmount
	) throws IOException {
		final BigDecimal exRate = exRateProvider.getExRate(currency);
		final BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		final LocalDateTime localDateTime = LocalDateTime.now(clock).plusMinutes(30);

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
