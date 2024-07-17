package org.delivery.db.store;

import java.math.BigDecimal;

import org.delivery.db.BaseEntity;
import org.delivery.db.store.vo.StoreCategory;
import org.delivery.db.store.vo.StoreStatus;
import org.hibernate.annotations.ColumnDefault;

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
@Table(name = "store")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Store extends BaseEntity {

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 150, nullable = false)
	private String address;

	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private StoreStatus status;

	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private StoreCategory category;

	@ColumnDefault("0")
	private double star;

	@Column(length = 200, nullable = false)
	private String thumbnailUrl;

	@Column(precision = 11, scale = 4, nullable = false)
	private BigDecimal minimumAmount;

	@Column(precision = 11, scale = 4, nullable = false)
	private BigDecimal minimumDeliveryAmount;

	@Column(length = 20)
	private String phoneNumber;

}
