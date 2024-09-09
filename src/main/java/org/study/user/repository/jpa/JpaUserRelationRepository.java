package org.study.user.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.user.repository.entity.UserRelationEntity;
import org.study.user.repository.entity.UserRelationId;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationId> {
}
