package com.example.exception.Controller;

import com.example.exception.Model.API;
import com.example.exception.Model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAPIController {
    // User DTO의 정보를 가진 Data 생성
    private static List<UserResponse> userList = List.of(
        UserResponse.builder()
                .id("1")
                .age(10)
                .name("홍길동")
                .build(),
        UserResponse.builder()
                .id("2")
                .age(10)
                .name("유관순")
                .build()
    );

    // 정보를 가진 API의 상태를 체크하기 위한 API DTO를 응답
    @GetMapping("/id/{userId}")
    public API<UserResponse> getUser(
        @PathVariable String userId
    ) {
        // GlobalException Test
        //if(true) throw new RuntimeException("test");

        // localhost:8080/api/user/id/{userId}에서 userId가
        // static으로 불러온 userList.id와 동일한 값이 있는지 필터링
        // 존재한다면 맨 처음 값을 가져옴.
        var user = userList.stream()
                .filter(
                        it -> it.getId().equals(userId)
                )
                .findFirst()
                .get();

        System.out.println(user);

        // 예외가 발생하지 않는다면 OK value를 가진 API 객체 return
        API<UserResponse> response = API.<UserResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.name())
                .data(user)
                .build();

        return response;
    }
}
