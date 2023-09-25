package com.example.validation.controller;

import com.example.validation.model.API;
import com.example.validation.model.UserRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @PostMapping("")
    // error 발생시 data 값을 null값을 취하지않고 API 제네릭을
    // API<? extends Object>로 와일드카드를 취해도 됨.
    public API<UserRegisterRequest> register(
        @Valid          // 검증을 하겠다는 어노테이션
        @RequestBody
        UserRegisterRequest userrgisterRequest

        // Interface, Valid의 결과를 담음
        // 에러 has, count 등을 볼 수 있음.
        // Business Logie이 더러워짐. Exception으로 넘김.
        //BindingResult bindingResult
    ) {
        log.info("init : {}", userrgisterRequest);

        // binding check -> exception
        /*if(bindingResult.hasErrors()) {
            var errorMessageList = bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(it -> {
                        var format = "%s : { %s }은 %s";
                        var message = String.format(
                                format,
                                it.getField(),
                                it.getRejectedValue(),
                                it.getDefaultMessage()
                        );
                        return message;
                    }).collect(Collectors.toList());
            var error = API.Error
                    .builder()
                    .errorMessage(errorMessageList)
                    .build();
            var errorResponse = API.
                    <UserRegisterRequest>builder()
                    .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .resultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .data(null)
                    .error(error)
                    .build();
            return errorResponse;
        }*/

        API<UserRegisterRequest> reponse = API.
                <UserRegisterRequest>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .data(userrgisterRequest)
                .build();
        log.info("api_log : {}", reponse);
        return reponse;
    }
}