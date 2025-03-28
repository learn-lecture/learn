package org.delivery.api.domain.sotremenu.dto.response;

import java.math.BigDecimal;

import org.delivery.db.storemenu.vo.StoreMenuStatus;

public record StoreMenuResponse(
	Long id,
	Long storeId,
	String name,
	BigDecimal amount,
	StoreMenuStatus status,
	String thumbnailUrl,
	int likeCount,
	int sequence
) {
}
