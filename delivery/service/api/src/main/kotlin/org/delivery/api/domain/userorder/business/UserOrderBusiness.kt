package org.delivery.api.domain.userorder.business

import org.delivery.api.common.Log
import org.delivery.api.domain.ordermenu.converter.OrderMenuConverter
import org.delivery.api.domain.ordermenu.service.OrderMenuService
import org.delivery.api.domain.sotremenu.converter.StoreMenuConverter
import org.delivery.api.domain.sotremenu.service.StoreMenuService
import org.delivery.api.domain.store.converter.StoreConverter
import org.delivery.api.domain.store.service.StoreService
import org.delivery.api.domain.userorder.converter.UserOrderConverter
import org.delivery.api.domain.userorder.dto.req.UserOrderRequest
import org.delivery.api.domain.userorder.dto.resp.UserOrderDetailResponse
import org.delivery.api.domain.userorder.dto.resp.UserOrderResponse
import org.delivery.api.domain.userorder.producer.UserOrderProducer
import org.delivery.api.domain.userorder.service.UserOrderService
import org.delivery.common.annotation.Business
import org.delivery.db.order.UserOrder
import org.delivery.db.storemenu.StoreMenu
import org.delivery.db.user.User

@Business
class UserOrderBusiness(
    private val userOrderService: UserOrderService,
    private val userOrderConverter: UserOrderConverter,

    private val storeMenuService: StoreMenuService,
    private val storeMenuConverter: StoreMenuConverter,

    private val orderMenuService: OrderMenuService,
    private val orderMenuConverter: OrderMenuConverter,

    private val storeService: StoreService,
    private val storeConverter: StoreConverter,

    private val userOrderProducer: UserOrderProducer
) {

    companion object : Log

    fun userOrder(request: UserOrderRequest?, user: User?): UserOrderResponse {
        val store = storeService.getStoreWithThrow(request?.storeId)
        val storeMenus: List<StoreMenu> = getStoreMenus(request)
        val orderResult = userOrderConverter.toEntity(user, store, storeMenus)
            .run { userOrderService.order(this) };

        storeMenus.forEach {
            val entity = orderMenuConverter.toEntity(orderResult, it)
            orderMenuService.order(entity)
        }

        userOrderProducer.sendOrder(orderResult)

        return userOrderConverter.toResponse(orderResult)
    }

    private fun getStoreMenus(request: UserOrderRequest?): List<StoreMenu> {
        return request?.storeMenus
            ?.mapNotNull { storeMenuService.getStoreMenuWithThrow(it) }
            ?: emptyList()
    }

    fun current(user: User?): List<UserOrderDetailResponse> {
        return userOrderService.current(user?.id)
            .map { getUserOrderDetailResponse(it) }
            .toList()
    }

    fun history(user: User): List<UserOrderDetailResponse> {
        return userOrderService.history(user.id)
            .map { getUserOrderDetailResponse(it) }
            .toList()
    }

    fun read(user: User, orderId: Long?): UserOrderDetailResponse {
        return getUserOrderDetailResponse(
            userOrderService.getUserOrderWithOutStatusWithThrow(orderId, user.id)
        )
    }

    private fun getUserOrderDetailResponse(userOrder: UserOrder): UserOrderDetailResponse {
        return UserOrderDetailResponse(
            userOrderConverter.toResponse(userOrder),
            storeConverter.toResponse(userOrder.store),
            storeMenuConverter.toResponse(userOrder.userOrderMenus
                .map { it.storeMenu }
                .toList()
            )
        )
    }

}