package org.demo.jpashop.repository.order.query;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.demo.jpashop.domain.Address;
import org.demo.jpashop.domain.OrderStatus;

@Data
@EqualsAndHashCode(of = "orderId")
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

    public OrderQueryDto(OrderQueryDto dto, List<OrderItemQueryDto> items) {
        this.orderId = dto.getOrderId();
        this.name = dto.getName();
        this.orderDate = dto.getOrderDate();
        this.orderStatus = dto.getOrderStatus();
        this.address = dto.getAddress();
        this.orderItems = items;
    }

    public OrderQueryDto(OrderFlatDto dto) {
        this.orderId = dto.getOrderId();
        this.name = dto.getName();
        this.orderDate = dto.getOrderDate();
        this.orderStatus = dto.getOrderStatus();
        this.address = dto.getAddress();
    }

}
