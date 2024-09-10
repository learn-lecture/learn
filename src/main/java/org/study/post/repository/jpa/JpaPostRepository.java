package org.study.post.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.post.domain.Post;
import org.study.post.repository.entity.post.PostEntity;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
}
