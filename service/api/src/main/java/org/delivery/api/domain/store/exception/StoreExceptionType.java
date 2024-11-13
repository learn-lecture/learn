package org.delivery.api.domain.store.exception;

import lombok.Getter;
import org.delivery.common.api.ResultType;

@Getter
public enum StoreExceptionType implements ResultType {

    NOT_FOUND_EXCEPTION(1201, "유효하지 않은 매장입니다."),
    NULL_POINT_EXCEPTION(1202, "매장 정보가 필요합니다.");

    private final int code;
    private final String message;

    StoreExceptionType(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

}
