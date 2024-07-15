package tobyspring.tobyspringtwo.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public interface ExRateProvider {
	BigDecimal getExRate(final String currency);
}
