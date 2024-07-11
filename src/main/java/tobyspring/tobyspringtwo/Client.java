package tobyspring.tobyspringtwo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.tobyspringtwo.payment.Payment;
import tobyspring.tobyspringtwo.payment.PaymentService;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException {
		final BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
		final PaymentService paymentService = beanFactory.getBean(PaymentService.class);

		final Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println("payment1: " + payment);
		System.out.println("----------------------------");

		TimeUnit.SECONDS.sleep(1);

		final Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println("payment2: " + payment2);
		System.out.println("----------------------------");

		TimeUnit.SECONDS.sleep(2);

		final Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println("payment3: " + payment3);
	}

}
