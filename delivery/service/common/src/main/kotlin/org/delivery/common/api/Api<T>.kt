package org.delivery.common.api

import jakarta.validation.Valid

class Api<T>(
    val result: Result? = null,

    @field: Valid
    val body: T
) {

    companion object {
        @JvmStatic
        fun <T> ok(resultType: ResultType, body: T): Api<T> {
            return Api(Result.of(resultType), body)
        }
    }

}