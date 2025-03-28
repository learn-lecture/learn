package org.study.post.repository;

import static org.study.post.repository.entity.comment.QCommentEntity.commentEntity;
import static org.study.post.repository.entity.like.QLikeEntity.likeEntity;
import static org.study.user.repository.entity.QUserEntity.userEntity;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.post.repository.comment.CommentQueryRepository;
import org.study.post.repository.entity.like.LikeTarget;
import org.study.post.ui.dto.GetContentResponseDto;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GetContentResponseDto> getComments(Long postId, Long userId, Long lastContentId) {
        return queryFactory.select(
                Projections.fields(
                    GetContentResponseDto.class,
                    commentEntity.id.as("id"),
                    commentEntity.content.as("content"),
                    userEntity.id.as("userId"),
                    userEntity.name.as("userName"),
                    userEntity.profileImage.as("profileImage"),
                    commentEntity.regDt.as("createdAt"),
                    commentEntity.updDt.as("updatedAt"),
                    commentEntity.likeCount.as("likeCount"),
                    likeEntity.isNotNull().as("isLikedByMe")
                ))
            .from(commentEntity)
            .join(userEntity).on(commentEntity.author.id.eq(userEntity.id))
            .leftJoin(likeEntity).on(hasLike(userId))
            .where(
                commentEntity.post.id.eq(postId),
                hasLastData(lastContentId)
            )
            .orderBy(commentEntity.id.desc())
            .limit(10)
            .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }

        return commentEntity.id.lt(lastId);
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }

        return commentEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq(LikeTarget.COMMENT.name()))
            .and(likeEntity.id.userId.eq(userId));
    }

}
