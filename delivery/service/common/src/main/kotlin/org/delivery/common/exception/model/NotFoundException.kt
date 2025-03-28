package org.delivery.common.exception.model

import org.delivery.common.api.ResultType

class NotFoundException(
    exceptionType: ResultType
) : BaseException(exceptionType)
