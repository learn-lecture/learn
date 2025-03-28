package org.delivery.apigateway.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(objectMapper: ObjectMapper): WebClient {
        return WebClient.builder()
            .codecs { configurer ->
                configurer.defaultCodecs().jackson2JsonEncoder(
                    Jackson2JsonEncoder(objectMapper)
                )
                configurer.defaultCodecs().jackson2JsonDecoder(
                    Jackson2JsonDecoder(objectMapper)
                )
            }
            .build()
    }

}