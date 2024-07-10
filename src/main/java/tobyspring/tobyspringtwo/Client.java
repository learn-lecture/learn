package tobyspring.tobyspringtwo;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

	public static void main(String[] args) throws IOException {
		final ObjectFactory objectFactory = new ObjectFactory();
		final PaymentService paymentService = objectFactory.paymentService();
		final Payment prepare = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(prepare);
	}

}
