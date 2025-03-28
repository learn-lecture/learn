package org.delivery.storeadmin.domain.userordermenu.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.db.ordermenu.OrderMenu;
import org.delivery.db.ordermenu.OrderMenuRepository;
import org.delivery.db.ordermenu.vo.OrderMenuStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderMenuService {

    private final OrderMenuRepository orderMenuRepository;

    public List<OrderMenu> getUserOrderMenus(Long userOrderId) {
        return orderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, OrderMenuStatus.REGISTERED);
    }

}
