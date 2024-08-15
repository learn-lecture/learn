package org.delivery.api.domain.userorder.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.userorder.exception.UserOrderExceptionType;
import org.delivery.api.exception.model.NotFoundException;
import org.delivery.db.order.UserOrder;
import org.delivery.db.order.UserOrderRepository;
import org.delivery.db.order.vo.UserOrderStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public UserOrder getUserOrderWithThrow(final Long id, final Long userId) {
        return userOrderRepository.findAllByIdAndStatusAndUserId(id, UserOrderStatus.REGISTERED, userId)
                .orElseThrow(() -> new NotFoundException(UserOrderExceptionType.NOT_FOUND_EXCEPTION));
    }

    public List<UserOrder> getUserOrders(final Long userId) {
        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }

    public List<UserOrder> getUserOrders(final Long userId, final List<UserOrderStatus> status) {
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, status);
    }

    public UserOrder order(final UserOrder userOrder) {
        return Optional.ofNullable(userOrder)
                .map(it -> {
                    it.setStatus(UserOrderStatus.ORDER);
                    it.setOrderedAt(LocalDateTime.now());
                    return userOrderRepository.save(it);
                }).orElseThrow(() -> new NotFoundException(UserOrderExceptionType.NOT_FOUND_EXCEPTION));
    }

}
