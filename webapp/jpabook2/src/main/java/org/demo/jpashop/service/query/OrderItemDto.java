package org.demo.jpashop.service.query;

import lombok.Getter;
import org.demo.jpashop.domain.OrderItem;

@Getter
public class OrderItemDto {

    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemDto(OrderItem orderItem) {
        this.itemName = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }

}