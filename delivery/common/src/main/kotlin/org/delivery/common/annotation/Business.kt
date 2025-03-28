package org.delivery.common.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Service

@Service
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Business(
    @get:AliasFor(annotation = Service::class)
    val value: String = ""
)
