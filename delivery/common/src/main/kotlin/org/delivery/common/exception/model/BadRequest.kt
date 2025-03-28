package org.delivery.common.exception.model

import org.delivery.common.api.ResultType

class BadRequestException(
    exceptionType: ResultType
) : BaseException(exceptionType)
