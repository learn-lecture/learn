package org.demo.jpashop.repository.order.query;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.demo.jpashop.domain.Address;
import org.demo.jpashop.domain.OrderStatus;

@Data
@AllArgsConstructor
public class OrderFlatDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private String itemName;
    private int orderPrice;
    private int count;

}
