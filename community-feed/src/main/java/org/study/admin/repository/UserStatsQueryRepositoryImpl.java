package org.study.admin.repository;

import static org.study.user.repository.entity.QUserEntity.userEntity;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import org.study.admin.ui.query.UserStatsQueryRepository;
import org.study.common.utils.TimeCalculator;

@Repository
@RequiredArgsConstructor
public class UserStatsQueryRepositoryImpl implements UserStatsQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays) {
        return jpaQueryFactory
                .select(
                        Projections.fields(GetDailyRegisterUserResponseDto.class,
                                userEntity.regDate.as("date"),
                                userEntity.count().as("count")
                        )
                )
                .from(userEntity)
                .where(userEntity.regDate.after(TimeCalculator.getDateDaysAgo(beforeDays)))
                .groupBy(userEntity.regDate)
                .orderBy(userEntity.regDate.asc())
                .fetch();
    }

}
