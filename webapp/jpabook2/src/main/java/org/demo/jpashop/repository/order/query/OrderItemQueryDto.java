package org.demo.jpashop.repository.order.query;

import lombok.Data;

@Data
public class OrderItemQueryDto {

    private Long orderId;
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemQueryDto(Long orderId, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public OrderItemQueryDto(OrderFlatDto dto) {
        this.orderId = dto.getOrderId();
        this.itemName = dto.getItemName();
        this.orderPrice = dto.getOrderPrice();
        this.count = dto.getCount();
    }
}
