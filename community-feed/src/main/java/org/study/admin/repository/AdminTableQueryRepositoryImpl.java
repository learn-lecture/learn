package org.study.admin.repository;

import static org.study.auth.repository.entity.QUserAuthEntity.userAuthEntity;
import static org.study.post.repository.entity.post.QPostEntity.postEntity;
import static org.study.user.repository.entity.QUserEntity.userEntity;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.admin.ui.dto.GetUserTableRequestDto;
import org.study.admin.ui.dto.posts.GetPostTableRequestDto;
import org.study.admin.ui.dto.posts.GetPostTableResponseDto;
import org.study.admin.ui.dto.users.GetUserTableListResponseDto;
import org.study.admin.ui.dto.users.GetUserTableResponseDto;
import org.study.admin.ui.query.AdminTableQueryRepository;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public GetUserTableListResponseDto<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto) {
        int totalCount = jpaQueryFactory.select(userEntity.id).from(userEntity).where(likeName(dto.getName())).fetch().size();

        List<Long> ids = jpaQueryFactory.select(userEntity.id)
                .from(userEntity)
                .where(likeName(dto.getName()))
                .orderBy(userEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        List<GetUserTableResponseDto> result = jpaQueryFactory.select(
                        Projections.fields(GetUserTableResponseDto.class,
                            userEntity.id.as("id"),
                            userAuthEntity.email.as("email"),
                            userEntity.name.as("name"),
                            userAuthEntity.role.as("role"),
                            userEntity.regDt.as("createdAt"),
                            userEntity.updDt.as("updatedAt"),
                            userAuthEntity.lastLoginDt.as("lastLoginAt")
                        )
                )
                .from(userEntity)
                .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
                .where(userEntity.id.in(ids))
                .orderBy(userEntity.id.desc())
                .fetch();

        return new GetUserTableListResponseDto<>(totalCount, result);
    }

    @Override
    public GetUserTableListResponseDto<GetPostTableResponseDto> getPostTableData(GetPostTableRequestDto dto) {
        int totalCount = jpaQueryFactory.select(postEntity.id)
                .from(postEntity)
                .where(eqPostId(dto.getPostId()))
                .fetch()
                .size();

        List<Long> ids = jpaQueryFactory.select(postEntity.id)
                .from(postEntity)
                .where(eqPostId(dto.getPostId()))
                .orderBy(postEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        List<GetPostTableResponseDto> result = jpaQueryFactory.select(
                        Projections.fields(GetPostTableResponseDto.class,
                                postEntity.id.as("postId"),
                                userEntity.id.as("userId"),
                                userEntity.name.as("userName"),
                                postEntity.content.as("content"),
                                postEntity.regDt.as("createdAt"),
                                postEntity.updDt.as("updatedAt")
                        )
                )
                .from(postEntity)
                .join(userEntity).on(postEntity.author.id.eq(userEntity.id))
                .where(postEntity.id.in(ids))
                .orderBy(postEntity.id.desc())
                .fetch();

        return new GetUserTableListResponseDto<>(totalCount, result);
    }

    private static BooleanExpression eqPostId(Long id) {
        if (id == null) {
            return null;
        }
        return postEntity.id.eq(id);
    }

    private BooleanExpression likeName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        return userEntity.name.like(name + "%");
    }

}
