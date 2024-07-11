package tobyspring.tobyspringtwo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tobyspring.tobyspringtwo.exrate.CachedExRateProvider;
import tobyspring.tobyspringtwo.payment.ExRateProvider;
import tobyspring.tobyspringtwo.exrate.WebApiExRateProvider;
import tobyspring.tobyspringtwo.payment.PaymentService;

@Configuration
@ComponentScan
public class ObjectFactory {

	@Bean
	public PaymentService paymentService() {
		return new PaymentService(cachedExRateProvider());
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
