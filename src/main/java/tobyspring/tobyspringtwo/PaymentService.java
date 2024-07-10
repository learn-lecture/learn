package tobyspring.tobyspringtwo;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

abstract public class PaymentService {

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

	abstract BigDecimal getExRate(final String currency) throws IOException;

}
