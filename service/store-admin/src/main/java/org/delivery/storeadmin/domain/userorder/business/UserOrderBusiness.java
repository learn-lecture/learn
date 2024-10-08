package org.delivery.storeadmin.domain.userorder.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.db.order.UserOrder;
import org.delivery.storeadmin.domain.sse.connection.ifs.UserSseConnectionPool;
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;
import org.delivery.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.storeadmin.domain.storemenu.dto.StoreMenuResponse;
import org.delivery.storeadmin.domain.storemenu.service.StoreMenuService;
import org.delivery.storeadmin.domain.userorder.converter.UserOrderConverter;
import org.delivery.storeadmin.domain.userorder.dto.UserOrderDetailResponse;
import org.delivery.storeadmin.domain.userorder.dto.UserOrderResponse;
import org.delivery.storeadmin.domain.userorder.service.UserOrderService;
import org.delivery.storeadmin.domain.userordermenu.service.UserOrderMenuService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserSseConnectionPool userSseConnectionPool;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final UserOrderConverter userOrderConverter;

    public void pushUserOrder(UserOrderMessage userOrderMessage) {
        Long userOrderId = userOrderMessage.getUserOrderId();
        UserOrder userOrder = userOrderService.getUserOrder(userOrderId);

        UserOrderResponse userOrderResponse = userOrderConverter.toResponse(userOrder);
        List<StoreMenuResponse> storeMenuResponses = userOrderMenuService.getUserOrderMenus(userOrderId)
            .stream()
            .map(userOrderMenu -> storeMenuService.getStoreMenuWithThrow(userOrderMenu.getStoreMenuId()))
            .map(storeMenuConverter::toResponse)
            .toList();

        UserOrderDetailResponse response = new UserOrderDetailResponse(userOrderResponse, storeMenuResponses);
        log.info("response = {}", response);

        UserSseConnection connection = userSseConnectionPool.getConnection(userOrder.getStoreId().toString());
        connection.sendMessage(response);
    }
}
