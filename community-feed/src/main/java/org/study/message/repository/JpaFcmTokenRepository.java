package org.study.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.message.repository.entity.FcmTokenEntity;

public interface JpaFcmTokenRepository extends JpaRepository<FcmTokenEntity, Long> {
}
