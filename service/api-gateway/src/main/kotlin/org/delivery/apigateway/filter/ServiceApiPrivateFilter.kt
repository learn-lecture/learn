package org.delivery.apigateway.filter

import org.delivery.apigateway.account.model.TokenDto
import org.delivery.apigateway.account.model.TokenValidationRequest
import org.delivery.apigateway.account.model.TokenValidationResponse
import org.delivery.apigateway.common.Log
import org.delivery.apigateway.exception.TokenException
import org.delivery.common.exception.model.BadRequestException
import org.delivery.common.exception.model.NotFoundException
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Component
class ServiceApiPrivateFilter : AbstractGatewayFilterFactory<ServiceApiPrivateFilter.Config>(Config::class.java) {

    companion object : Log

    class Config

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            // 토큰 유무 체크
            val uri = exchange.request.uri
            log.info("service api private filter route uri: {}", uri)

            val token = generatedAccessToken(exchange.request)
            log.info("Authorization: {}", token)


            // 토큰 유효성 검증
            val accountApiUrl = UriComponentsBuilder.fromUriString("http://localhost")
                .port(8083)
                .path("/internal-api/token/validation")
                .build()
                .encode()
                .toUriString()

            val webClient = WebClient.builder().baseUrl(accountApiUrl).build()
            val request = TokenValidationRequest(tokenDto = TokenDto(token = token, expiredAt = LocalDateTime.now()))
            log.info("\ntoken:{}\nrequest: {}", token, request);

            webClient.post()
                .body(Mono.just(request), object : ParameterizedTypeReference<TokenValidationRequest>() {})
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                    { status: HttpStatusCode ->
                        status.isError
                    },
                    { response: ClientResponse ->
                        response.bodyToMono(object : ParameterizedTypeReference<Any>() {})
                            .flatMap { error ->
                                log.error("{}", error)
                                Mono.error(BadRequestException(TokenException.INVALID_TOKEN))
                            }
                    }
                )
                .bodyToMono(object : ParameterizedTypeReference<TokenValidationResponse>() {})
                .flatMap { response ->
                    log.info("response: {}", response)
                    // 사용자 정보 추가
                    val mono = chain.filter(exchange)
                    mono
                }

        }

    }

    private fun generatedAccessToken(request: ServerHttpRequest): String? {
        val accessToken = getHeaders(request).first()

        if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer ")) {
            return accessToken.split(" ")[1]
        }
        throw NotFoundException(TokenException.NOT_FOUND_TOKEN)
    }

    private fun getHeaders(request: ServerHttpRequest): List<String> {
        val headers = request.headers["Authorization"] ?: listOf()

        if (headers.isEmpty()) {
            throw NotFoundException(TokenException.NOT_FOUND_TOKEN)
        }
        return headers
    }

}