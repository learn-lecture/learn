package org.delivery.account.domain.token.business

import lombok.RequiredArgsConstructor
import org.delivery.account.domain.token.controller.model.TokenValidationRequest
import org.delivery.account.domain.token.controller.model.TokenValidationResponse
import org.delivery.account.domain.token.service.TokenService
import org.delivery.common.annotation.Business

@Business
@RequiredArgsConstructor
class TokenBusiness(
    private val tokenService: TokenService,
) {

    fun validationToken(tokenValidationRequest: TokenValidationRequest?): TokenValidationResponse {
        val result = tokenService.validationToken(tokenValidationRequest?.tokenDto?.token)
        return TokenValidationResponse(result)
    }

}
