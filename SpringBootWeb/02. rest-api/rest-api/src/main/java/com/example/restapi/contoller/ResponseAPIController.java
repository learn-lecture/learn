package com.example.restapi.contoller;

import com.example.restapi.Model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@RestController - JSON으로 응답하겠다.
@Controller // html로 응답할 수 있음.
@RequestMapping("/api/v1")
// http://localhost:8080/api/v1
public class ResponseAPIController {

    @GetMapping("") //주소 그대로 매핑
    // @RequestMapping(path = "", method = RequestMethod.GET) 과 동일
    // RequestMapping 을 사용할 경우 method를 명확하게.
    //Object 형식으로 return - JSON
    public UserRequest user() {

        var user = new UserRequest();
        user.setUserName("홍길동");
        user.setUserAge(10);
        user.setEmail("hong@gmail.com");

        return user;
    }
    @GetMapping("string")
    //String return, 200 OK
    public String user2() {

        var user = new UserRequest();
        user.setUserName("홍길동");
        user.setUserAge(10);
        user.setEmail("hong@gmail.com");

        log.info("user : {}", user);

        return user.toString();
    }

    @GetMapping("custom")
    // ResponseEntity return, 200 OK
    @ResponseBody
    // Controller로 선언시 응답을 JSON으로 전달하게 하기 위한 어노테이션
    public ResponseEntity<UserRequest> user3() {
    // ResponseEntity로 return은 주로 예외처리시
        var user = new UserRequest();
        user.setUserName("홍길동");
        user.setUserAge(10);
        user.setEmail("hong@gmail.com");

        log.info("user : {}", user);
        
        //status로 응답 컨트롤
        var response = ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header("x-custom", "hi")
                .body(user);

        return response;
    }


}