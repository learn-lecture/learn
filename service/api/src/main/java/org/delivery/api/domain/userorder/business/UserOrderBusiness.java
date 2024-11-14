package org.delivery.api.domain.userorder.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.ordermenu.converter.OrderMenuConverter;
import org.delivery.api.domain.ordermenu.service.OrderMenuService;
import org.delivery.api.domain.sotremenu.converter.StoreMenuConverter;
import org.delivery.api.domain.sotremenu.service.StoreMenuService;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.api.domain.userorder.converter.UserOrderConverter;
import org.delivery.api.domain.userorder.dto.req.UserOrderRequest;
import org.delivery.api.domain.userorder.dto.resp.UserOrderDetailResponse;
import org.delivery.api.domain.userorder.dto.resp.UserOrderResponse;
import org.delivery.api.domain.userorder.producer.UserOrderProducer;
import org.delivery.api.domain.userorder.service.UserOrderService;
import org.delivery.common.annotation.Business;
import org.delivery.db.order.UserOrder;
import org.delivery.db.ordermenu.OrderMenu;
import org.delivery.db.store.Store;
import org.delivery.db.storemenu.StoreMenu;
import org.delivery.db.user.User;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {

    private static final Logger log = LoggerFactory.getLogger(UserOrderBusiness.class);
    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final OrderMenuService orderMenuService;
    private final OrderMenuConverter orderMenuConverter;
    private final StoreService storeService;
    private final StoreConverter storeConverter;
    private final UserOrderProducer userOrderProducer;

    public UserOrderResponse userOrder(final UserOrderRequest request, final User user) {
        final List<StoreMenu> storeMenus = getStoreMenus(request);
        final Store store = storeService.getStoreWithThrow(request.storeId());
        final UserOrder order = userOrderConverter.toEntity(user, store, storeMenus);
        final UserOrder orderResult = userOrderService.order(order);

        storeMenus.forEach(it -> {
            OrderMenu entity = orderMenuConverter.toEntity(orderResult, it);
            orderMenuService.order(entity);
        });

        userOrderProducer.sendOrder(orderResult);

        return userOrderConverter.toResponse(orderResult);
    }

    private List<StoreMenu> getStoreMenus(UserOrderRequest request) {
        return request.storeMenus()
            .stream()
            .map(storeMenuService::getStoreMenuWithThrow)
            .toList();
    }

    public List<UserOrderDetailResponse> current(final User user) {
        return userOrderService.current(user.getId())
            .stream()
            .map(this::getUserOrderDetailResponse)
            .toList();
    }

    public List<UserOrderDetailResponse> history(final User user) {
        return userOrderService.history(user.getId())
            .stream()
            .map(this::getUserOrderDetailResponse)
            .toList();
    }

    public UserOrderDetailResponse read(final User user, final Long orderId) {
        return getUserOrderDetailResponse(
            userOrderService.getUserOrderWithOutStatusWithThrow(orderId, user.getId())
        );
    }

    @NotNull
    private UserOrderDetailResponse getUserOrderDetailResponse(UserOrder userOrder) {
        final var storeMenus = userOrder.getUserOrderMenus()
            .stream()
            .map(OrderMenu::getStoreMenu)
            .toList();

        return new UserOrderDetailResponse(
            userOrderConverter.toResponse(userOrder),
            storeConverter.toResponse(userOrder.getStore()),
            storeMenuConverter.toResponse(storeMenus)
        );
    }

}
