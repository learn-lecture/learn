package org.delivery.api.domain.sotremenu.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StoreMenuRegisterRequest(
	@NotNull Long storeId,
	@NotBlank String name,
	@NotNull BigDecimal amount,
	@NotBlank String thumbnailUrl
) {
}
