package tobyspring.tobyspringtwo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.tobyspringtwo.config.DataConfig;
import tobyspring.tobyspringtwo.db.OrderRepository;
import tobyspring.tobyspringtwo.order.Order;
import tobyspring.tobyspringtwo.payment.PaymentService;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {

        final BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        final OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        orderRepository.save(order);

        System.out.println(order);
    }

}
