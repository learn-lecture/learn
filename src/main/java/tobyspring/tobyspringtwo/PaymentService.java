package tobyspring.tobyspringtwo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

	public Payment prepare(
		final Long orderId,
		final String currency,
		final BigDecimal foreignCurrencyAmount
	) {
		// todo 환율가져오기
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

	public static void main(String[] args) {
		final PaymentService paymentService = new PaymentService();
		final Payment prepare = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(prepare);
	}

}
