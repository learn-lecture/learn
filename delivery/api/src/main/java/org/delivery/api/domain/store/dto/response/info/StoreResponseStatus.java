package org.delivery.api.domain.store.dto.response.info;

import lombok.Getter;
import org.delivery.common.api.ResultType;

@Getter
public enum StoreResponseStatus implements ResultType {

    SEARCH_STORE_SUCCESS(300, "매장 조회에 성공했습니다."),
    CREATE_STORE_SUCCESS(301, "매장 등록에 성공했습니다.");

    private final int code;
    private final String message;

    StoreResponseStatus(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

}
