package tobyspring.tobyspringtwo;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.tobyspringtwo.config.DataConfig;
import tobyspring.tobyspringtwo.db.OrderRepository;
import tobyspring.tobyspringtwo.order.Order;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {

        final BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        final OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
        final JpaTransactionManager jpaTransactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try {
            new TransactionTemplate(jpaTransactionManager).execute(status -> {
                final Order order = new Order("100", BigDecimal.TEN);
                orderRepository.save(order);
                System.out.println(order);

                final Order order2 = new Order("100", BigDecimal.ONE);
                orderRepository.save(order2);

                return null;
            });
        } catch (ConstraintViolationException e) {
            System.out.println("왜 ConstraintViolationException ?");
        }
    }

}
