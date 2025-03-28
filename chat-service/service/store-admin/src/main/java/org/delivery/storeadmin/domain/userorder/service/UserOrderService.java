package org.delivery.storeadmin.domain.userorder.service;

import lombok.RequiredArgsConstructor;
import org.delivery.db.order.UserOrder;
import org.delivery.db.order.UserOrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public UserOrder getUserOrder(Long userOrderId) {
        return userOrderRepository.findById(userOrderId)
            .orElseThrow(IllegalArgumentException::new);
    }

}
