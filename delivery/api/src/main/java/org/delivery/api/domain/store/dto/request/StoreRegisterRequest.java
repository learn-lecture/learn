package org.delivery.api.domain.store.dto.request;

import java.math.BigDecimal;

import org.delivery.db.store.vo.StoreCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StoreRegisterRequest(
	@NotBlank
	String name,
	@NotBlank
	String address,
	@NotNull
	StoreCategory category,
	@NotBlank
	String thumbnailUrl,
	@NotNull
	BigDecimal minimumAmount,
	@NotNull
	BigDecimal minimumDeliveryAmount,
	@NotBlank
	String phoneNumber
) {
}
