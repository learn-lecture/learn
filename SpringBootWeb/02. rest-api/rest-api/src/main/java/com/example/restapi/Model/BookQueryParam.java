package com.example.restapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data // lombok 사용 어노테이션
@AllArgsConstructor // 전체 파라미터를 입력받는 생성자
@NoArgsConstructor // 파라미터를 받지 않는 기본 생성자
public class BookQueryParam {
    private String category;
    private String issuedYear;
    private String issuedMonth;
    private String issuedDay;
}
