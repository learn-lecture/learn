package tobyspring.tobyspringtwo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import tobyspring.tobyspringtwo.db.JpaOrderRepository;
import tobyspring.tobyspringtwo.order.OrderRepository;
import tobyspring.tobyspringtwo.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderService orderService(final JpaTransactionManager jpaTransactionManager) {
        return new OrderService(jpaOrderRepository(), jpaTransactionManager);
    }

    @Bean
    public OrderRepository jpaOrderRepository() {
        return new JpaOrderRepository();
    }

}
