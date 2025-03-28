package com.example.validation.exception;

import com.example.validation.model.API;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {
    // Valid에 대한 예외처리 어노테이션 아규먼트
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<API> validationException(
        MethodArgumentNotValidException exception
    ) {
        log.error("", exception);

        // Error Message Formatting
        // FieldErrors이므로 data가 여러개.
        // 하나하나 매핑하여 모아서 List로 변환
        var errorMessageList = exception
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

        // Error는 static Inner class라서 API 생성시 같이 메모리에 적재되므로
        // 바로 접근 할 수 있음. Lombok의 builder로 getter와 비슷하게
        // 변수에 접근할 수 있음. 이 때, Lombok builder 기능으로
        // 괄호를 이용하여 데이터를 전달할 수 있음.
        var error = API.Error
                .builder()
                .errorMessage(errorMessageList)
                .build();

        var errorResponse = API.
                builder()
                .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .resultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(error)
                .build();

        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
