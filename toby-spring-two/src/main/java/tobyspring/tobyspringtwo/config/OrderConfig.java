package tobyspring.tobyspringtwo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tobyspring.tobyspringtwo.db.JdbcOrderRepository;
import tobyspring.tobyspringtwo.order.OrderRepository;
import tobyspring.tobyspringtwo.order.OrderServiceTxProxy;
import tobyspring.tobyspringtwo.order.OrderService;
import tobyspring.tobyspringtwo.order.OrderServiceImpl;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
@EnableTransactionManagement
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(final DataSource dataSource) {
        //return new JpaOrderRepository();
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(final OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }


}
