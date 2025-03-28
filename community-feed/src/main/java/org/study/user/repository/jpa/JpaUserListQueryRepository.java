package org.study.user.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.study.user.application.dto.GetUserListResponseDto;
import org.study.user.repository.entity.UserEntity;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query("""
        select new org.study.user.application.dto.GetUserListResponseDto(u.name, u.profileImage)
        from UserRelationEntity ur
        inner join UserEntity u ON ur.followingUserId = u.id
        where ur.followingUserId = :userId""")
    List<GetUserListResponseDto> getFollowingUsers(Long userId);

    @Query("""
        select new org.study.user.application.dto.GetUserListResponseDto(u.name, u.profileImage)
        from UserRelationEntity ur
        inner join UserEntity u ON ur.followerUserId = u.id
        where ur.followerUserId = :userId""")
    List<GetUserListResponseDto> getFollowUsers(Long userId);

}
