package org.delivery.db.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.order.vo.UserOrderStatus;
import org.delivery.db.ordermenu.OrderMenu;
import org.delivery.db.store.Store;

@Entity
@Table(name = "user_order")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(callSuper = true)
public class UserOrder extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false, columnDefinition = "varchar(50)")
    @Setter
    private UserOrderStatus status;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    @Setter
    private LocalDateTime orderedAt;

    @Setter
    private LocalDateTime acceptedAt;

    @Setter
    private LocalDateTime cookingStartedAt;

    @Setter
    private LocalDateTime deliveryStartedAt;

    @Setter
    private LocalDateTime receivedAt;

    @OneToMany(mappedBy = "userOrder")
    @Exclude
    @JsonIgnore
    private List<OrderMenu> userOrderMenus = new ArrayList<>();

}
