package org.delivery.api.domain.userorder.converter

import org.delivery.api.domain.userorder.dto.resp.UserOrderResponse
import org.delivery.common.annotation.Converter
import org.delivery.db.order.UserOrder
import org.delivery.db.store.Store
import org.delivery.db.storemenu.StoreMenu
import org.delivery.db.user.User

@Converter
class UserOrderConverter {

    fun toEntity(user: User?, store: Store?, storeMenus: List<StoreMenu>?): UserOrder {
        val totalAmount = storeMenus
            ?.mapNotNull { storeMenu -> storeMenu.amount }
            ?.reduce { acc, bigDecimal -> acc.plus(bigDecimal) }

        return UserOrder(
            userId = user?.id,
            store = store,
            amount = totalAmount
        );
    }

    fun toResponse(userOrder: UserOrder?): UserOrderResponse {
        return UserOrderResponse(
            id = userOrder?.id,
            status = userOrder?.status,
            amount = userOrder?.amount,
            orderedAt = userOrder?.orderedAt,
            acceptedAt = userOrder?.acceptedAt,
            cookingStartedAt = userOrder?.cookingStartedAt,
            deliveryStartedAt = userOrder?.deliveryStartedAt,
            receivedAt = userOrder?.receivedAt
        )
    }

}