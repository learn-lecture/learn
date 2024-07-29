package org.delivery.db.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.delivery.db.BaseEntity;
import org.delivery.db.order.vo.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "`order`")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order extends BaseEntity {

	@Column(nullable = false)
	private Long userId;

	@Enumerated(EnumType.STRING)
	@Column(length = 50, nullable = false)
	private OrderStatus status;

	@Column(precision = 11, scale = 4, nullable = false)
	private BigDecimal amount;

	private LocalDateTime orderedAt;

	private LocalDateTime acceptedAt;

	private LocalDateTime cookingStartedAt;

	private LocalDateTime deliveryStartedAt;

	private LocalDateTime receivedAt;

}
