package tobyspring.tobyspringtwo.exrate;

import tobyspring.tobyspringtwo.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {

	@Override
	public BigDecimal getExRate(final String currency) throws IOException {
		if (!currency.equals("USD")) {
			throw new IllegalArgumentException("No");
		}
		return BigDecimal.valueOf(1000);
	}

}
