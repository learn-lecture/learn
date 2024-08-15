package org.delivery.api.domain.ordermenu.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.db.ordermenu.OrderMenu;
import org.delivery.db.ordermenu.OrderMenuRepository;
import org.delivery.db.ordermenu.vo.OrderMenuStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderMenuService {

    private final OrderMenuRepository orderMenuRepository;

    public List<OrderMenu> getOrderMenu(final Long orderId) {
        return orderMenuRepository.findAllByUserOrderIdAndStatus(orderId, OrderMenuStatus.REGISTERED);
    }

}
