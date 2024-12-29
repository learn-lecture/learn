package org.study.admin.ui.query;

import org.study.admin.ui.dto.GetUserTableRequestDto;
import org.study.admin.ui.dto.users.GetUserTableListResponseDto;
import org.study.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {

    GetUserTableListResponseDto<GetUserTableResponseDto> getUserTableDate(GetUserTableRequestDto dto);

}
