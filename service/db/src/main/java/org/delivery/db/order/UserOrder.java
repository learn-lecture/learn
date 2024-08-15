package org.delivery.db.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.order.vo.UserOrderStatus;

@Entity
@Table(name = "user_order")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserOrder extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
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

}
