package tobyspring.tobyspringtwo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tobyspring.tobyspringtwo.exrate.CachedExRateProvider;
import tobyspring.tobyspringtwo.payment.ExRateProvider;
import tobyspring.tobyspringtwo.exrate.WebApiExRateProvider;
import tobyspring.tobyspringtwo.payment.PaymentService;

import java.time.Clock;

@Configuration
@ComponentScan
public class PaymentConfig {

	@Bean
	public PaymentService paymentService() {
		return new PaymentService(cachedExRateProvider(), clock());
	}

	private Clock clock() {
		return Clock.systemDefaultZone();
	}

	@Bean
	public ExRateProvider cachedExRateProvider() {
		return new CachedExRateProvider(exRateProvider());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new WebApiExRateProvider();
	}

}
