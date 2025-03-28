package com.example.exception.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping(path = "")
    public void hello() {
        var list = List.of("hello");

        // Spring 내에서 자동으로 exception을 catch 해서 알려줌.
        var element = list.get(1);
        log.info("element : {}", element);

        // 직접 에러를 발생하는 방법. 하나하나 정리하기 비효율적임.
        try{
            var elem = list.get(1);
        }catch (Exception e) {
            log.error("{}", e);
        }
    }
}
