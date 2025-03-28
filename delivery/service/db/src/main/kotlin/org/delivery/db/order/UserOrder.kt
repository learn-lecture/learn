package org.delivery.db.order

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.delivery.db.order.vo.UserOrderStatus
import org.delivery.db.ordermenu.OrderMenu
import org.delivery.db.store.Store
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "user_order")
class UserOrder(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:Column(nullable = false)
    var userId: Long? = null,

    @field:JoinColumn(nullable = false)
    @field:ManyToOne
    var store: Store? = null,

    @field:Enumerated(EnumType.STRING)
    @field:Column(length = 50, nullable = false, columnDefinition = "varchar(50)")
    var status: UserOrderStatus? = null,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var amount: BigDecimal? = null,

    var orderedAt: LocalDateTime? = null,

    var acceptedAt: LocalDateTime? = null,

    var cookingStartedAt: LocalDateTime? = null,

    var deliveryStartedAt: LocalDateTime? = null,

    var receivedAt: LocalDateTime? = null,

    @field:OneToMany(mappedBy = "userOrder")
    @field:JsonIgnore
    var userOrderMenus: MutableList<OrderMenu> = mutableListOf(),
) {

    override fun toString(): String {
        return "UserOrder(receivedAt=$receivedAt, deliveryStartedAt=$deliveryStartedAt, cookingStartedAt=$cookingStartedAt, acceptedAt=$acceptedAt, orderedAt=$orderedAt, amount=$amount, status=$status, store=$store, userId=$userId, id=$id)"
    }

}