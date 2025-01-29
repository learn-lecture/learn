package org.demo.jpashop.service.query;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.demo.jpashop.domain.Address;
import org.demo.jpashop.domain.Order;
import org.demo.jpashop.domain.OrderStatus;

@Data
public class OrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getName();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress();
        this.orderItems = order.getOrderItems().stream()
                .map(OrderItemDto::new)
                .toList();
    }
}