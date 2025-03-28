package org.delivery.common.exception.model

data class ErrorResponse(
    val fieldName: String,
    val message: String
) {

    override fun toString(): String {
        return "ErrorResponse{" +
                "fieldName='" + fieldName + '\'' +
                ", message='" + message + '\'' +
                '}'
    }

}