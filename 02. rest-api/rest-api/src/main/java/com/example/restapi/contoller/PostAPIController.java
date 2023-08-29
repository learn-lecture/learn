package com.example.restapi.contoller;

import com.example.restapi.Model.BookRequest;
import com.example.restapi.Model.UserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostAPIController {
    // Post를 하기 위한 어노테이션
    // http://localhost:8080/api/post
    @PostMapping("/post")
    public String post(
            // http body로 들어오는 데이터를 객체에 매핑
            // default = JSON, html도 가능.
            @RequestBody BookRequest bookRequest
    ) {
        System.out.println(bookRequest);
        return bookRequest.toString();
    }

    // TODO RequestBody로 사용자의 이름, 전화번호, 이메일을 받음.
    @PostMapping("/user")
    public UserRequest user(
            @RequestBody UserRequest userRequest
    ) {
        System.out.println(userRequest);
        return userRequest;
    }
}
