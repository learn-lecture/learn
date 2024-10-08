package org.delivery.api.domain.userorder.dto.req;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record UserOrderRequest(
    @NotNull Long storeId,
    @NotNull List<Long> storeMenus
) {
}
