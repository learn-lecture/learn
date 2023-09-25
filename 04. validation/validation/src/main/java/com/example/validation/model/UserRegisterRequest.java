package com.example.validation.model;

import com.example.validation.annotation.PhoneNumber;
import com.example.validation.annotation.YearMonth;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterRequest {

    //@NotNull                      // != null
    //@NotEmpty                     // != null && != ""
    //@NotBlank                       // != null && != "" && != " "
    private String name;
    private String nickName;

    @Size(min = 1, max = 12)        // 1자리 ~ 12자리
    @NotBlank
    private String password;

    @NotNull                        // Intager 이므로 Not Null
    @Min(1) @Max(100)               // Value = 1 ~ 100
    private Integer age;

    @Email
    private String email;

    // 휴대폰 번호를 검증하는 정규식
    //@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 양식에 맞지 않음.")
    @PhoneNumber
    private String phoneNumber;

    // 자바에서 기본적으로 ISO 8601이라는 Date Type을 사용함.
    // Json Data : 2023-09-25 21:47:00 -> 2023-09-25T21:47:00
    // 오늘 이후의 날짜만 받겠다는 어노테이션
    //@FutureOrPresent
    @YearMonth
    //private LocalDateTime registerAt;
    private String registerAt;

    // Valid, 두 값 중 한 값이라도 있을 경우 검증 O
    // Method 시작 명에 무조건 is를 추가해야함.
    @AssertTrue(message = "name or nick_name은 반드시 1개 이상 존재해야합니다.")
    public boolean isNameCheck() {
        if(!Objects.isNull(name) && !name.isBlank()) return true;
        if(!Objects.isNull(nickName) && !nickName.isBlank()) return true;
        return false;
    }
}
