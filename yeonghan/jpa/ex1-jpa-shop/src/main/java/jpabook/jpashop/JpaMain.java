package jpabook.jpashop;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

public class JpaMain {

    public static void main(String[] args) {
        TransactionManager.executeInTransaction(em -> {
            Order order = new Order();
            //order.addOrderItem(new OrderItem());
        });
    }

}