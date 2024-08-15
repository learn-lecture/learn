package org.delivery.api.domain.userorder.converter;

import java.math.BigDecimal;
import java.util.List;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.userorder.dto.resp.UserOrderResponse;
import org.delivery.db.order.UserOrder;
import org.delivery.db.storemenu.StoreMenu;
import org.delivery.db.user.User;

@Converter
public class UserOrderConverter {

    public UserOrder toEntity(final User user, final List<StoreMenu> storeMenus) {
        final BigDecimal totalAmount = storeMenus.stream()
                .map(StoreMenu::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return UserOrder.builder()
                .userId(user.getId())
                .amount(totalAmount)
                .build();
    }

    public UserOrderResponse toResponse(final UserOrder userOrder) {
        return new UserOrderResponse(
                userOrder.getId(),
                userOrder.getStatus(),
                userOrder.getAmount(),
                userOrder.getOrderedAt(),
                userOrder.getAcceptedAt(),
                userOrder.getCookingStartedAt(),
                userOrder.getDeliveryStartedAt(),
                userOrder.getReceivedAt()
        );
    }

}
