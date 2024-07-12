package tobyspring.tobyspringtwo.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class Payment {

	private Long orderId;
	private String currency;
	private BigDecimal foreignCurrencyAmount;
	private BigDecimal exRate;
	private BigDecimal convertedAmount;
	private LocalDateTime validUntil;

	public static Payment createPrepared(
			final Long orderId,
			final String currency,
			final BigDecimal foreignCurrencyAmount,
			final BigDecimal exRate,
			final LocalDateTime now
	) {
		final BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		final LocalDateTime validUntil = now.plusMinutes(30);

		return Payment.builder()
				.orderId(orderId)
				.currency(currency)
				.foreignCurrencyAmount(foreignCurrencyAmount)
				.exRate(exRate)
				.convertedAmount(convertedAmount)
				.validUntil(validUntil)
				.build();
	}

	public boolean isValid(final Clock clock) {
		return LocalDateTime.now(clock).isBefore(this.validUntil);
	}

}
