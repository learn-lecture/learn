package org.delivery.api.common.health

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/open-api")
class HealthOpenApiController {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/health")
    fun health() {
        logger.info("Health OpenAPIController called")
    }

}