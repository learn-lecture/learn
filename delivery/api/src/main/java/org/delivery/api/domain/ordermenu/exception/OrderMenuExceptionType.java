package org.delivery.api.domain.ordermenu.exception;

import lombok.Getter;
import org.delivery.common.api.ResultType;

@Getter
public enum OrderMenuExceptionType implements ResultType {

    NOT_FOUND_EXCEPTION(1211, "주문 메뉴 정보를 찾지 못했습니다.");

    private final int code;
    private final String message;

    OrderMenuExceptionType(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

}
