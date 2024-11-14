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

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {

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
        final List<StoreMenu> storeMenus = request.storeMenus().stream()
            .map(storeMenuService::getStoreMenuWithThrow)
            .toList();
        Store store = storeService.getStoreWithThrow(request.storeId());
        final UserOrder order = userOrderConverter.toEntity(user, store, storeMenus);
        final UserOrder orderResult = userOrderService.order(order);

        storeMenus.forEach(it -> {
            OrderMenu entity = orderMenuConverter.toEntity(orderResult, it);
            orderMenuService.order(entity);
        });

        userOrderProducer.sendOrder(orderResult);

        return userOrderConverter.toResponse(orderResult);
    }

    public List<UserOrderDetailResponse> current(final User user) {
        var current = userOrderService.current(user.getId());
        return current.stream().map(it -> {
            var orderMenu = orderMenuService.getOrderMenu(it.getId());
            var storeMenus = orderMenu.stream()
                .map(orderMenuEntity ->
                    storeMenuService.getStoreMenuWithThrow(orderMenuEntity.getStoreMenu().getId()))
                .toList();
            var store = storeService.getStoreWithThrow(storeMenus.stream().findFirst().get().getStore().getId());
            return new UserOrderDetailResponse(
                userOrderConverter.toResponse(it),
                storeConverter.toResponse(store),
                storeMenuConverter.toResponse(storeMenus)
            );
        }).toList();
    }

    public List<UserOrderDetailResponse> history(final User user) {
        var history = userOrderService.history(user.getId());
        return history.stream().map(it -> {
            var orderMenu = orderMenuService.getOrderMenu(it.getId());
            var storeMenus = orderMenu.stream()
                .map(orderMenuEntity ->
                    storeMenuService.getStoreMenuWithThrow(orderMenuEntity.getStoreMenu().getId()))
                .toList();
            var store = storeService.getStoreWithThrow(storeMenus.stream().findFirst().get().getStore().getId());
            return new UserOrderDetailResponse(
                userOrderConverter.toResponse(it),
                storeConverter.toResponse(store),
                storeMenuConverter.toResponse(storeMenus)
            );
        }).toList();
    }

    public UserOrderDetailResponse read(final User user, final Long orderId) {
        var userOrder = userOrderService.getUserOrderWithOutStatusWithThrow(orderId, user.getId());
        var orderMenu = orderMenuService.getOrderMenu(userOrder.getId());
        var storeMenus = orderMenu.stream()
            .map(orderMenuEntity -> storeMenuService.getStoreMenuWithThrow(orderMenuEntity.getStoreMenu().getId()))
            .toList();
        var store = storeService.getStoreWithThrow(storeMenus.stream().findFirst().get().getStore().getId());

        return new UserOrderDetailResponse(
            userOrderConverter.toResponse(userOrder),
            storeConverter.toResponse(store),
            storeMenuConverter.toResponse(storeMenus)
        );
    }

}
