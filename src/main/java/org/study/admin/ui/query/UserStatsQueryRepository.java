package org.study.admin.ui.query;

import java.util.List;
import org.study.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

public interface UserStatsQueryRepository {

    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);

}
