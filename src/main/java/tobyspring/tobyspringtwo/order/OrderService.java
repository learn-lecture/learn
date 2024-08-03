package tobyspring.tobyspringtwo.order;

import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public Order createOrder(final String no, final BigDecimal total) {
        final Order order = new Order(no, total);

        this.orderRepository.save(order);
        return order;
    }

    public List<Order> createOrders(List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager).execute(status ->
            reqs.stream().map(req -> createOrder(req.no(), req.total())).toList()
        );
    }

}

