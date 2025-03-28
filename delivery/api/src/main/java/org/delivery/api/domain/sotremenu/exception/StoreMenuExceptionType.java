package org.delivery.api.domain.sotremenu.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.delivery.common.api.ResultType;

@Getter
@AllArgsConstructor
public enum StoreMenuExceptionType implements ResultType {

    NULL_POINT_EXCEPTION(1201, "메뉴 정보가 필요합니다."),
    INVALID_MENU_EXCEPTION(1202, "없는 메뉴정보 입니다.");

    private int code;
    private String message;

}
