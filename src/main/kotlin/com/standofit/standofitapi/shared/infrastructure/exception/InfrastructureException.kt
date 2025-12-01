package com.standofit.standofitapi.shared.infrastructure.exception

import com.standofit.standofitapi.shared.error.BaseError

open class InfrastructureException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : BaseError(code, message, cause)
