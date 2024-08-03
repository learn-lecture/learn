package tobyspring.tobyspringtwo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import tobyspring.tobyspringtwo.db.JdbcOrderRepository;
import tobyspring.tobyspringtwo.order.OrderRepository;
import tobyspring.tobyspringtwo.order.OrderSErviceTxProxy;
import tobyspring.tobyspringtwo.order.OrderService;
import tobyspring.tobyspringtwo.order.OrderServiceImpl;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(final DataSource dataSource) {
        //return new JpaOrderRepository();
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(
            final PlatformTransactionManager transactionManager,
            final OrderRepository orderRepository
    ) {
        return new OrderSErviceTxProxy(
            new OrderServiceImpl(orderRepository),
            transactionManager
        );
    }


}
