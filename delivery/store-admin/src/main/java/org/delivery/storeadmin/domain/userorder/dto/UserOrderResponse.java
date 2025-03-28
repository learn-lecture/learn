package org.delivery.storeadmin.domain.userorder.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.delivery.db.order.vo.UserOrderStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class UserOrderResponse {

    private Long id;
    private Long userId;
    private Long storeId;
    private UserOrderStatus status;
    private BigDecimal amount;
    private LocalDateTime orderedAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime cookingStartedAt;
    private LocalDateTime deliveryStartedAt;
    private LocalDateTime receivedAt;

}
