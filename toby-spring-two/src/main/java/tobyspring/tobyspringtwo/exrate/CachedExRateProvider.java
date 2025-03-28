package tobyspring.tobyspringtwo.exrate;

import tobyspring.tobyspringtwo.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;
    private BigDecimal cachedExRate;
    private LocalDateTime cachedExpiryTime;

    public CachedExRateProvider(final ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(final String currency) {
        if (cachedExRate == null || cachedExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExRate = this.target.getExRate(currency);
            cachedExpiryTime = LocalDateTime.now().plusSeconds(3);

            System.out.println("Cache updated");
        }
        return cachedExRate;
    }

}
