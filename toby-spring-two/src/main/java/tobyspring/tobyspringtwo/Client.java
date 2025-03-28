package tobyspring.tobyspringtwo;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.tobyspringtwo.config.PaymentConfig;
import tobyspring.tobyspringtwo.payment.Payment;
import tobyspring.tobyspringtwo.payment.PaymentService;

public class Client {

	public static void main(String[] args) {
		final BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
		final PaymentService paymentService = beanFactory.getBean(PaymentService.class);

		final Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println("payment: " + payment);
	}

}
