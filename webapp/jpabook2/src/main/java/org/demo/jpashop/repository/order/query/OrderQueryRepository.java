package org.demo.jpashop.repository.order.query;

import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.demo.jpashop.domain.Order;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();
        result.forEach(o -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select new org.demo.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
                        + " from OrderItem oi"
                        + " join oi.item i"
                        + " where oi.order.id = :orderId", OrderItemQueryDto.class
        )
        .setParameter("orderId", orderId)
        .getResultList();
    }

    private List<OrderQueryDto> findOrders() {
        // dto 직접 조회 시 fetch 사용 불가
        return em.createQuery(
                "select new org.demo.jpashop.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)"
                        + " from Order o"
                        + " join o.member m"
                        + " join o.delivery d", OrderQueryDto.class
        ).getResultList();
    }

}
