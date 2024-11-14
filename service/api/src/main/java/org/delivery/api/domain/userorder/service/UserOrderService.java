package org.delivery.api.domain.userorder.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.userorder.exception.UserOrderExceptionType;
import org.delivery.common.exception.model.NotFoundException;
import org.delivery.db.order.UserOrder;
import org.delivery.db.order.UserOrderRepository;
import org.delivery.db.order.vo.UserOrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserOrderService {

    private static final Logger log = LoggerFactory.getLogger(UserOrderService.class);
    private final UserOrderRepository userOrderRepository;

    public UserOrder getUserOrderWithOutStatusWithThrow(final Long id, final Long userId) {
        return Optional.ofNullable(userOrderRepository.findAllByIdAndUserId(id, userId))
            .orElseThrow(() -> new NotFoundException(UserOrderExceptionType.NOT_FOUND_EXCEPTION));
    }

    public UserOrder getUserOrderWithThrow(final Long id, final Long userId) {
        return Optional.ofNullable(userOrderRepository.findAllByIdAndStatusAndUserId(
                id,
                UserOrderStatus.REGISTERED, userId
            ))
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
                log.info("userOrder: {}", it);
                it.setStatus(UserOrderStatus.ORDER);
                it.setOrderedAt(LocalDateTime.now());
                return userOrderRepository.save(it);
            }).orElseThrow(() -> new NotFoundException(UserOrderExceptionType.NOT_FOUND_EXCEPTION));
    }

    public UserOrder setStatus(final UserOrder userOrder, final UserOrderStatus status) {
        userOrder.setStatus(status);
        return userOrderRepository.save(userOrder);
    }

    public UserOrder accept(final UserOrder userOrder) {
        userOrder.setAcceptedAt(LocalDateTime.now());
        return setStatus(userOrder, UserOrderStatus.ACCEPT);
    }

    public UserOrder cooking(final UserOrder userOrder) {
        userOrder.setCookingStartedAt(LocalDateTime.now());
        return setStatus(userOrder, UserOrderStatus.COOKING);
    }

    public UserOrder delivery(final UserOrder userOrder) {
        userOrder.setDeliveryStartedAt(LocalDateTime.now());
        return setStatus(userOrder, UserOrderStatus.DELIVERY);
    }

    public UserOrder receive(final UserOrder userOrder) {
        userOrder.setReceivedAt(LocalDateTime.now());
        return setStatus(userOrder, UserOrderStatus.RECEIVE);
    }

    public List<UserOrder> current(final Long userId) {
        return getUserOrders(
            userId,
            List.of(
                UserOrderStatus.ORDER,
                UserOrderStatus.COOKING,
                UserOrderStatus.DELIVERY,
                UserOrderStatus.RECEIVE
            )
        );
    }

    public List<UserOrder> history(final Long userId) {
        return getUserOrders(userId, List.of(UserOrderStatus.RECEIVE));
    }

}
