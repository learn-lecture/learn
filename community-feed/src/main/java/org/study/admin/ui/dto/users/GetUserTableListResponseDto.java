package org.study.admin.ui.dto.users;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserTableListResponseDto<T> {

    private int totalCount;
    private List<T> totalData;

}
