package org.delivery.apigateway.account.model

data class TokenValidationRequest(
    var tokenDto: TokenDto? = null
)