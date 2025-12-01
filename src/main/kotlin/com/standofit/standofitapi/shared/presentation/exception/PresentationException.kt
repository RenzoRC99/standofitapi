package com.standofit.standofitapi.shared.presentation.exception

import com.standofit.standofitapi.shared.error.BaseError

open class PresentationException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : BaseError(code, message, cause)
