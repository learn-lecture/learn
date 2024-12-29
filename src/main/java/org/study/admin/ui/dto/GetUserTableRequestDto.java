package org.study.admin.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.study.common.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserTableRequestDto extends Pageable {

    private String name;

}
