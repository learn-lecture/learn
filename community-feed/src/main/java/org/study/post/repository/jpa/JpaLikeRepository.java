package org.study.post.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.post.repository.entity.like.LikeEntity;
import org.study.post.repository.entity.like.LikeId;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeId> {
}
