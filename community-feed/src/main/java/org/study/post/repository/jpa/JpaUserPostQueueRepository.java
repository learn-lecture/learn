package org.study.post.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.post.repository.entity.post.UserPostQueueEntity;

public interface JpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long> {

    void deleteAllByUserIdAndAuthorId(Long userId, Long authorId);

}
