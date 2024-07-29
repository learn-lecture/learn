package org.delivery.db.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrederRepository extends JpaRepository<Order, Long> {
}
