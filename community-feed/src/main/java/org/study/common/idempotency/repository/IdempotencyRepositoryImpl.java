package org.study.common.idempotency.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.common.idempotency.Idempotency;
import org.study.common.idempotency.IdempotencyRepository;
import org.study.common.idempotency.repository.entity.IdemPotencyEntity;

@Repository
@RequiredArgsConstructor
public class IdempotencyRepositoryImpl implements IdempotencyRepository {

    private final JpaIdempotencyRepository jpaIdempotencyRepository;

    @Override
    public Idempotency getByKey(String key) {
        return jpaIdempotencyRepository.findByIdempotencyKey(key)
                .map(IdemPotencyEntity::toIdempotency)
                .orElse(null);
    }

    @Override
    public void save(Idempotency idempotency) {
        jpaIdempotencyRepository.save(new IdemPotencyEntity(idempotency));
    }

}
