package com.example.exception.Exception;

import com.example.exception.Model.API;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
// Default 값
@Order(value = Integer.MAX_VALUE)
public class GlobalException {
    // Exception Class와 하위 예외 Class에 대한 처리 메서드를 정의 (Global)
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<API> exception(Exception e) {
        log.error("RestApiExceptionHandler", e);

        var response = API.builder()
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();

        // 예외 처리 객체를 생성해서 반환함.
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
