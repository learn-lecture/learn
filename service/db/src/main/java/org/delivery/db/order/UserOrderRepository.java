package org.delivery.db.order;

import java.util.List;
import java.util.Optional;
import org.delivery.db.order.vo.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    List<UserOrder> findAllByUserIdAndStatusOrderByIdDesc(final Long userId, final UserOrderStatus status);

    List<UserOrder> findAllByUserIdAndStatusInOrderByIdDesc(final Long userId, final List<UserOrderStatus> status);

    Optional<UserOrder> findAllByIdAndStatusAndUserId(final Long id, final UserOrderStatus status, final Long userId);

}
