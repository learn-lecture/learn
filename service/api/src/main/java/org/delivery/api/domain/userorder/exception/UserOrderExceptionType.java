package org.delivery.api.domain.userorder.exception;

import lombok.Getter;
import org.delivery.api.common.api.ResultType;

@Getter
public enum UserOrderExceptionType implements ResultType {

    NOT_FOUND_EXCEPTION(1111, "사용자의 주문 정보를 찾지 못했습니다.");

    private final int code;
    private final String message;

    UserOrderExceptionType(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

}
