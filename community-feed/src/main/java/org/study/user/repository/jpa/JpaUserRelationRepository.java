package org.study.user.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.study.user.repository.entity.UserRelationEntity;
import org.study.user.repository.entity.UserRelationId;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationId> {

    @Query("select u.followingUserId from UserRelationEntity u where u.followerUserId = :userId")
    List<Long> findFollowers(Long userId);

}
