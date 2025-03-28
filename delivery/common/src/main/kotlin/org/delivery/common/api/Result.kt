package org.delivery.common.api

data class Result(
    val resultCode: Int,
    val resultMessage: String
) {

    companion object {
        @JvmStatic
        fun of(resultType: ResultType): Result {
            return Result(resultType.code, resultType.message)
        }
    }

}
