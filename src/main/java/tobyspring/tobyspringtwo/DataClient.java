package tobyspring.tobyspringtwo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.tobyspringtwo.config.DataConfig;
import tobyspring.tobyspringtwo.order.Order;
import tobyspring.tobyspringtwo.payment.PaymentService;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {

        final BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        final EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);
        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        final Order order = new Order("100", BigDecimal.TEN);
        em.persist(order);

        System.out.println(order);

        em.getTransaction().commit();
        em.close();
    }

}
