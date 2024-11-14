package org.delivery.storeadmin.domain.userorder.converter;

import org.delivery.db.order.UserOrder;
import org.delivery.storeadmin.domain.userorder.dto.UserOrderResponse;
import org.springframework.stereotype.Service;

@Service
public class UserOrderConverter {

    public UserOrderResponse toResponse(UserOrder userOrder) {
        return UserOrderResponse.builder()
            .id(userOrder.getId())
            .userId(userOrder.getUserId())
            .storeId(userOrder.getStore().getId())
            .status(userOrder.getStatus())
            .amount(userOrder.getAmount())
            .orderedAt(userOrder.getOrderedAt())
            .acceptedAt(userOrder.getAcceptedAt())
            .cookingStartedAt(userOrder.getCookingStartedAt())
            .deliveryStartedAt(userOrder.getDeliveryStartedAt())
            .receivedAt(userOrder.getReceivedAt())
            .build();
    }

}
