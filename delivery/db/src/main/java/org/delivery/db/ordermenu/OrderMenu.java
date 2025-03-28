package org.delivery.db.ordermenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.order.UserOrder;
import org.delivery.db.ordermenu.vo.OrderMenuStatus;
import org.delivery.db.storemenu.StoreMenu;

@Entity
@Table(name = "order_menu")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderMenu extends BaseEntity {

    @JoinColumn(nullable = false)
    @ManyToOne
    private UserOrder userOrder;

    @JoinColumn(nullable = false)
    @ManyToOne
    private StoreMenu storeMenu;

    @Setter
    @Column(length = 50, nullable = false, columnDefinition = "varchar(50)")
    @Enumerated(EnumType.STRING)
    private OrderMenuStatus status;

}
