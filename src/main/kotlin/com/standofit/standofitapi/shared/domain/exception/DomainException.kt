package com.standofit.standofitapi.shared.domain.exception

import com.standofit.standofitapi.shared.error.BaseError

open class DomainException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : BaseError(code, message, cause)
