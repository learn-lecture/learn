package org.delivery.db.ordermenu

import org.delivery.db.ordermenu.vo.OrderMenuStatus
import org.springframework.data.jpa.repository.JpaRepository

interface OrderMenuRepository : JpaRepository<OrderMenu, Long> {

    fun findAllByUserOrderIdAndStatus(userOrderId: Long?, status: OrderMenuStatus?): List<OrderMenu>

}