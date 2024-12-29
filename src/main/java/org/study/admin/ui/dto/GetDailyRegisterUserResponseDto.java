package org.study.admin.ui.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetDailyRegisterUserResponseDto {

    private LocalDate date;
    private Long count;

}
