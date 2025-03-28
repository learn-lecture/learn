package org.study.auth.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.auth.repository.entity.UserAuthEntity;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, String> {
}
