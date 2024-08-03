package tobyspring.tobyspringtwo.order;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.tobyspringtwo.config.OrderConfig;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private DataSource dataSource;

    @Test
    void createOrder() {
        Order order = orderService.createOrder("0100", BigDecimal.TEN);

        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders() {
        final List<OrderReq> orderReqs = List.of(
            new OrderReq("0200", BigDecimal.ONE),
            new OrderReq("0201", BigDecimal.TWO)
        );

        final List<Order> orders = orderService.createOrders(orderReqs);

        assertThat(orders).hasSize(2);
        orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0));
    }

    @Test
    void createDuplicatedOrders() {
        final List<OrderReq> orderReqs = List.of(
            new OrderReq("0300", BigDecimal.ONE),
            new OrderReq("0300", BigDecimal.TWO)
        );

        assertThatThrownBy(() -> orderService.createOrders(orderReqs))
            .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(dataSource);
        final Long count = client.sql("select count(*) from orders where no ='0300'").query(Long.class).single();
        assertThat(count).isEqualTo(0);
    }

}
