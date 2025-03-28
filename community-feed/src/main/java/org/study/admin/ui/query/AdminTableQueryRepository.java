package org.study.admin.ui.query;

import org.study.admin.ui.dto.GetUserTableRequestDto;
import org.study.admin.ui.dto.posts.GetPostTableRequestDto;
import org.study.admin.ui.dto.posts.GetPostTableResponseDto;
import org.study.admin.ui.dto.users.GetUserTableListResponseDto;
import org.study.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {

    GetUserTableListResponseDto<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);
    GetUserTableListResponseDto<GetPostTableResponseDto> getPostTableData(GetPostTableRequestDto dto);

}
