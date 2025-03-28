package org.delivery.api.domain.store.dto.response;

import java.math.BigDecimal;

import org.delivery.db.store.vo.StoreCategory;
import org.delivery.db.store.vo.StoreStatus;

public record StoreResponse(
	Long id,
	String name,
	String address,
	StoreStatus status,
	StoreCategory category,
	Double star,
	String thumbnailUrl,
	BigDecimal minimumAmount,
	BigDecimal minimumDeliveryAmount,
	String phoneNumber
) {
}
