package tobyspring.tobyspringtwo.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import tobyspring.tobyspringtwo.order.Order;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManagerFactory emf;

    public void save(final Order order) {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.persist(order);
            transaction.commit();
        } catch (final RuntimeException e) {
            if(transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        // todo Template - CallBack 구현해보기
    }

}
