package org.study.user.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.user.repository.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
