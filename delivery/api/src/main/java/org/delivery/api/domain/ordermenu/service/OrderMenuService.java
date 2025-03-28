package org.delivery.api.domain.ordermenu.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.ordermenu.exception.OrderMenuExceptionType;
import org.delivery.common.exception.model.NotFoundException;
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

    public void order(final OrderMenu orderMenu) {
        Optional.ofNullable(orderMenu)
            .map(it -> {
                it.setStatus(OrderMenuStatus.REGISTERED);
                return orderMenuRepository.save(it);
            })
            .orElseThrow(() -> new NotFoundException(OrderMenuExceptionType.NOT_FOUND_EXCEPTION));
    }

}
