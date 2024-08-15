package org.delivery.api.domain.userorder.dto.resp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.delivery.db.order.vo.UserOrderStatus;

public record UserOrderResponse(
        Long id,
        UserOrderStatus status,
        BigDecimal amount,
        LocalDateTime orderedAt,
        LocalDateTime acceptedAt,
        LocalDateTime cookingStartedAt,
        LocalDateTime deliveryStartedAt,
        LocalDateTime receivedAt
) {
}
