package org.demo.jpashop.repository;

import lombok.Getter;
import lombok.Setter;
import org.demo.jpashop.domain.OrderStatus;

@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

}
