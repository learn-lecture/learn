package org.demo.jpashop.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.demo.jpashop.domain.Order;
import org.demo.jpashop.domain.OrderItem;
import org.demo.jpashop.repository.OrderRepository;
import org.demo.jpashop.repository.OrderSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderApIController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(orderItem -> {orderItem.getItem().getName();});
        }
        return all;
    }

}
