package com.example.exception.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/2")
public class RestApiController2 {

    // http://localhost:8080/api/2/hello
    @GetMapping("/hello")
    public void hello() {
        throw new NumberFormatException("number format exception");
    }
/*  basePackages Test를 위해 잠시 주석처리
    // 명시적으로 처리 가능, 같은 Controller Level에서 작성
    // Global Level까지 가지 않고 해당 Controller 내 에서 해결 함.
    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity numberFormatException(NumberFormatException e) {
        log.error("RestApi2Controller", e);
        // ResponseEntity.status(200).build()와 같음.
        return ResponseEntity.ok().build();
    }
*/
}
