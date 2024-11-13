package org.delivery.api.domain.user.controller.model.info;

import lombok.Getter;
import org.delivery.common.api.ResultType;

@Getter
public enum UserDtoStatus implements ResultType {

    USER_REGISTERED_SUCCESS(200, "사용자 회원가입이 성공했습니다."),
    USER_LOGIN_SUCCESS(201, "사용자 로그인이 성공했습니다."),
    USER_PROFILE_SUCCESS(202, "사용자 정보 조회에 성공했습니다.");

    private final int code;
    private final String message;

    UserDtoStatus(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

}
