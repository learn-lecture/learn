package org.delivery.db.ordermenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.ordermenu.vo.OrderMenuStatus;

@Entity
@Table(name = "order_menu")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderMenu extends BaseEntity {

    @Column(nullable = false)
    private Long userOrderId;

    @Column(nullable = false)
    private Long storeMenuId;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    @Setter
    private OrderMenuStatus status;

}
