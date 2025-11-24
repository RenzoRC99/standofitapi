package com.standofit.standofitapi.shared.domain.exception

import com.standofit.standofitapi.shared.error.BaseError

open class DomainException(
    message: String,
    cause: Throwable? = null
) : BaseError(message, cause)
