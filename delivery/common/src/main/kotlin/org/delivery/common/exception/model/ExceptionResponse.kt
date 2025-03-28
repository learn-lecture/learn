package org.delivery.common.exception.model

import org.delivery.common.api.ResultType

data class ExceptionResponse(
    val errorCode: Int,
    val errorMessage: String
) {

    companion object {
        @JvmStatic
        fun from(errorType: ResultType) = ExceptionResponse(
            errorCode = errorType.code,
            errorMessage = errorType.message
        )
    }

}