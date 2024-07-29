package org.delivery.db.ordermenu;

import org.delivery.db.ordermenu.vo.OrderMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
}
