package tobyspring.tobyspringtwo.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tobyspring.tobyspringtwo.order.Order;
import tobyspring.tobyspringtwo.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Order order) {
        entityManager.persist(order);
    }

}
