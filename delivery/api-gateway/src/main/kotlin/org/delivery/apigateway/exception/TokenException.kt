package org.delivery.apigateway.exception

import org.delivery.common.api.ResultType

enum class TokenException(override val code: Int, override val message: String) : ResultType {
    NOT_FOUND_TOKEN(404, "토큰 정보가 없음"),
    INVALID_TOKEN(400, "유효하지 않은 토큰"),
}
