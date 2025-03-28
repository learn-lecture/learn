package com.example.restapi.contoller;

import com.example.restapi.Model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // log 어노테이션
@RestController
@RequestMapping("/api")
public class PutAPIController {
    @PutMapping("/put") // put 어노테이션
    public void put(
        @RequestBody UserRequest userRequest
    ) {
        // system 출력은 sout 실행 후 다음 메서드가 호출 됨.
        // 많이 출력할 수록 속도 저하
        // 로그백은 자체 버퍼를 가져서 더 빠름.
        // 버퍼 사이즈 설정 가능, 가득찰 경우 마찬가지로 느려짐
        // 로그백을 커스텀해서 원하는 형태로도 출력 가능
        // 시간 출력 가능, 어떤 스레드인지, 어떤 클래스인지 확인 가능
        log.info("Request : {}", userRequest);
    }
}
