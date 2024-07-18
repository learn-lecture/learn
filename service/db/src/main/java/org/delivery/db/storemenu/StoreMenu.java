package org.delivery.db.storemenu;

import java.math.BigDecimal;

import org.delivery.db.BaseEntity;
import org.delivery.db.storemenu.vo.StoreMenuStatus;
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
@Table(name = "store_menu")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StoreMenu extends BaseEntity {

	@Column(nullable = false)
	private Long storedId;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(precision = 11, scale = 4, nullable = false)
	private BigDecimal amount;

	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private StoreMenuStatus status;

	@Column(length = 200, nullable = false)
	private String thumbnailUrl;

	@ColumnDefault("0")
	private int likeCount;

	@ColumnDefault("0")
	private int sequence;
}
