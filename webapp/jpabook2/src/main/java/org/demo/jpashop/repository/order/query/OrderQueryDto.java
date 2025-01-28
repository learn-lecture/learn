package org.demo.jpashop.repository.order.query;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.demo.jpashop.domain.Address;
import org.demo.jpashop.domain.OrderStatus;

@Data
public class OrderQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
