package org.delivery.api.domain.userorder.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.ordermenu.converter.OrderMenuConverter;
import org.delivery.api.domain.ordermenu.service.OrderMenuService;
import org.delivery.api.domain.sotremenu.service.StoreMenuService;
import org.delivery.api.domain.userorder.converter.UserOrderConverter;
import org.delivery.api.domain.userorder.dto.req.UserOrderRequest;
import org.delivery.api.domain.userorder.dto.resp.UserOrderResponse;
import org.delivery.api.domain.userorder.service.UserOrderService;
import org.delivery.db.order.UserOrder;
import org.delivery.db.ordermenu.OrderMenu;
import org.delivery.db.storemenu.StoreMenu;
import org.delivery.db.user.User;

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final StoreMenuService storeMenuService;
    private final UserOrderConverter userOrderConverter;
    private final OrderMenuConverter orderMenuConverter;
    private final OrderMenuService orderMenuService;

    public UserOrderResponse userOrder(final UserOrderRequest request, final User user) {
        final List<StoreMenu> storeMenus = request.storeMenus().stream()
                .map(storeMenuService::getStoreMenuWithThrow)
                .toList();
        final UserOrder order = userOrderConverter.toEntity(user, storeMenus);
        final UserOrder orderResult = userOrderService.order(order);

        storeMenus.forEach(it -> {
            OrderMenu entity = orderMenuConverter.toEntity(orderResult, it);
            orderMenuService.order(entity);
        });

        return userOrderConverter.toResponse(orderResult);
    }
}
