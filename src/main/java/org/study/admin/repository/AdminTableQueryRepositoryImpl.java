package org.study.admin.repository;

import static org.study.auth.repository.entity.QUserAuthEntity.userAuthEntity;
import static org.study.user.repository.entity.QUserEntity.userEntity;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.admin.ui.dto.GetUserTableRequestDto;
import org.study.admin.ui.dto.users.GetUserTableListResponseDto;
import org.study.admin.ui.dto.users.GetUserTableResponseDto;
import org.study.admin.ui.query.AdminTableQueryRepository;
import org.study.user.repository.entity.QUserEntity;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public GetUserTableListResponseDto<GetUserTableResponseDto> getUserTableDate(GetUserTableRequestDto dto) {
        int totalCount = jpaQueryFactory.select(userEntity.id).from(userEntity).where(likeName(dto.getName())).fetch().size();
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
                .where(likeName(dto.getName()))
                .orderBy(userEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        return new GetUserTableListResponseDto<>(totalCount, result);
    }

    private BooleanExpression likeName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        return userEntity.name.like(name + "%");
    }

}
