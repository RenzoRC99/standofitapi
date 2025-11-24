package com.standofit.standofitapi.shared.error

open class BaseError(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
