package org.delivery.account.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.core.jackson.ModelResolver
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun modelResolver(objectMapper: ObjectMapper?): ModelResolver {
        return ModelResolver(objectMapper)
    }

    @Bean
    fun openAPI(): OpenAPI {
        val securityScheme = SecurityScheme().apply {
            type(SecurityScheme.Type.HTTP)
            `in`(SecurityScheme.In.HEADER)
            name("Authorization")
            scheme("bearer")
            bearerFormat("JWT")
            description("Bearer JWT")
        }

        val securityRequirement = SecurityRequirement().addList("bearerAuth")

        return OpenAPI().components(Components().addSecuritySchemes("bearerAuth", securityScheme))
            .security(listOf(securityRequirement))
    }

}