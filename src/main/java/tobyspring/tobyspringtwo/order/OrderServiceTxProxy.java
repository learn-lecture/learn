package tobyspring.tobyspringtwo.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceTxProxy implements OrderService {

	private final OrderService target;
	private final PlatformTransactionManager transactionManager;

	@Override
	public Order createOrder(final String no, final BigDecimal total) {
		return target.createOrder(no, total);
	}

	@Override
	public List<Order> createOrders(final List<OrderReq> reqs) {
		return new TransactionTemplate(transactionManager)
			.execute(status-> target.createOrders(reqs));
	}
}
