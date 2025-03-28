package org.delivery.api.domain.ordermenu.converter;

import org.delivery.common.annotation.Converter;
import org.delivery.db.order.UserOrder;
import org.delivery.db.ordermenu.OrderMenu;
import org.delivery.db.storemenu.StoreMenu;

@Converter
public class OrderMenuConverter {

    public OrderMenu toEntity(
        final UserOrder userOrder,
        final StoreMenu storeMenu
    ) {
        return OrderMenu.builder()
            .userOrder(userOrder)
            .storeMenu(storeMenu)
            .build();
    }

}
