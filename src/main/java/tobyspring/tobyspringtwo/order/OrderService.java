package tobyspring.tobyspringtwo.order;

import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.tobyspringtwo.db.OrderRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final JpaTransactionManager transactionManager;

    public Order createOrder(final String no, final BigDecimal total) {
        final Order order = new Order(no, total);

        return new TransactionTemplate(transactionManager).execute(status -> {
            this.orderRepository.save(order);
            return order;
        });
    }

}

