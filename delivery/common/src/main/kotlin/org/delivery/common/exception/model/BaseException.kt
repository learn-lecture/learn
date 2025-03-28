package org.delivery.common.exception.model

import org.delivery.common.api.ResultType

open class BaseException(
    val exceptionType: ResultType
) : RuntimeException(exceptionType.message)