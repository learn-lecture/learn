package org.delivery.api.domain.userorder.dto.resp.info;

import lombok.Getter;
import org.delivery.api.common.api.ResultType;

@Getter
public enum UserOrderResponseStatus implements ResultType {

    USER_ORDER_SUCCESS(300, "주문에 성공했습니다.");

    private final int code;
    private final String message;

    UserOrderResponseStatus(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

}
