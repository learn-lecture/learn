package org.delivery.api.domain.sotremenu.dto.response.info;

import lombok.Getter;
import org.delivery.common.api.ResultType;

@Getter
public enum StoreMenuResponseStatus implements ResultType {

    CREATE_MENU_SUCCESS(400, "메뉴 추가에 성공했습니다."),
    CREATE_STORE_SUCCESS(401, "매장 등록에 성공했습니다."),
    SEARCH_STORE_SUCCESS(402, "메뉴 조회에 성공했습니다.");

    private final int code;
    private final String message;

    StoreMenuResponseStatus(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

}
