package tobyspring.tobyspringtwo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tobyspring.tobyspringtwo.exrate.CachedExRateProvider;
import tobyspring.tobyspringtwo.payment.ExRateProvider;
import tobyspring.tobyspringtwo.exrate.WebApiExRateProvider;
import tobyspring.tobyspringtwo.payment.ExRateProviderStub;
import tobyspring.tobyspringtwo.payment.PaymentService;

import java.math.BigDecimal;

@Configuration
@ComponentScan
public class TestObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

}
