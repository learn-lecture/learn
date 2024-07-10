package tobyspring.tobyspringtwo;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExRateProvider {

	BigDecimal getExRate(final String currency) throws IOException {
		if (!currency.equals("USD")) {
			throw new IllegalArgumentException("No");
		}
		return BigDecimal.valueOf(1000);
	}

}
