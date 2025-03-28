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
	) {
		// todo exRateProvider 를 parameter 로 전달해서 처리하도록 해보기
		// todo clock 또한,
		final BigDecimal exRate = exRateProvider.getExRate(currency);
		return Payment.createPrepared(orderId, currency, foreignCurrencyAmount, exRate, LocalDateTime.now(clock));
	}

}
