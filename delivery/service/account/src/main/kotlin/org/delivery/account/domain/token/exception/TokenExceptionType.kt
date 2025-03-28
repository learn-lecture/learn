package org.delivery.account.domain.token.exception

import org.delivery.common.api.ResultType

enum class TokenExceptionType(override val code: Int, override val message: String) : ResultType {

    ACCESS_TOKEN_NULL_EXCEPTION(1100, "액세스 토큰 정보는 NULL 일 수 없습니다."),
    REFRESH_TOKEN_NULL_EXCEPTION(1101, "리프레시 토큰 정보는 NULL 일 수 없습니다."),
    INVALID_TOKEN_EXCEPTION(1102, "유효하지 않은 토큰 정보입니다."),
    NOTFOUND_TOKEN_EXCEPTION(1103, "토큰 헤더를 찾을 수 없습니다.")

}
