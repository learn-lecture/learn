package org.study.user.repository.jpa;

import static org.study.user.repository.entity.QUserEntity.userEntity;
import static org.study.user.repository.entity.QUserRelationEntity.userRelationEntity;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.user.application.dto.GetUserListResponseDto;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<GetUserListResponseDto> getUserFollowers(Long userId, Long lastFollowerId) {
        return jpaQueryFactory.select(
                Projections.fields(
                    GetUserListResponseDto.class
                )
            ).from(userRelationEntity)
            .join(userEntity).on(userRelationEntity.followingUserId.eq(userId))
            .where(
                userRelationEntity.followerUserId.eq(userId),
                hasLastData(lastFollowerId)
            ).orderBy(userEntity.id.desc())
            .limit(20)
            .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }
        return userEntity.id.lt(lastId);
    }

}
