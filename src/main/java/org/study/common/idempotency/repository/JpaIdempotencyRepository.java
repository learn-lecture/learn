package org.study.common.idempotency.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.study.common.idempotency.repository.entity.IdemPotencyEntity;

public interface JpaIdempotencyRepository extends JpaRepository<IdemPotencyEntity, Long> {

    Optional<IdemPotencyEntity> findByIdempotencyKey(String key);

}
