package org.study.common.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "Invalid Input Value"),
    NOT_FOUND(404, "Not Found Data"),
    INTERNAL_ERROR(500, "Unexpected Error");

    private final Integer code;
    private final String message;

}
