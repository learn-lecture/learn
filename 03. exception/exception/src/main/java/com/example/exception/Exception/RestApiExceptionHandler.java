package com.example.exception.Exception;

import com.example.exception.Controller.RestApiController;
import com.example.exception.Controller.RestApiController2;
import com.example.exception.Model.API;
import com.example.exception.Model.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
// @ControllerAdvice(annotations = RestController.class) 로도 사용가능
// Global하게 예외를 처리할 수 있음.
// Default Value 사용, 어떤 예외처리인지?
// basePackages 로 어떤 패키지의 내용만 처리할지?
//@RestControllerAdvice

// basePackages와 하위 패키지까지만 처리하겠다.
//@RestControllerAdvice(basePackages = "com.example.exception.Controller")

// 특정 class가 속한 패키지 및 하위패키지만 예외처리를 하겠다.
// 그 외에도 어노테이션을 이용해서 처리하는 방법도 있음.
@RestControllerAdvice(basePackageClasses = {RestApiController2.class, RestApiController.class})
// value = 1
@Order(1)
public class RestApiExceptionHandler {
    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    // IndexOutOfBounds Exception Class에 대한 처리 메서드만 정의함.
    // 이런 방법으로 원하는 예외에 대해서만 처리할 수 있음.
    public ResponseEntity outOfBound(IndexOutOfBoundsException e) {
        log.error("IndexOutOfBoundsException", e);
        return ResponseEntity.status(200).build();
    }

    // UserAPIController에 대한 예외처리.
    // data를 찾지 못했을 경우 NOT_FOUND 상태와
    // NOT_FOUND 정보를 가진 API 객체를 body에 리턴
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity NosuchElementException(NoSuchElementException e) {
        log.error("존재하지 않는 ID입니다.", e);
        var response = API.builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
