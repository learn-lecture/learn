package tobyspring.tobyspringtwo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.tobyspringtwo.config.OrderConfig;
import tobyspring.tobyspringtwo.order.Order;
import tobyspring.tobyspringtwo.order.OrderService;
import tobyspring.tobyspringtwo.order.OrderServiceImpl;

import java.math.BigDecimal;

public class OrderClient {

    public static void main(String[] args) {

        final BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        final OrderService service = beanFactory.getBean(OrderService.class);

        final Order order = service.createOrder("0100", BigDecimal.TEN);
        System.out.println(order);
    }

}
