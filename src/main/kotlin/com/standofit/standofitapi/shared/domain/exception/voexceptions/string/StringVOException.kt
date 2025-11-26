package com.standofit.standofitapi.shared.domain.exception.voexceptions.string

import com.standofit.standofitapi.shared.domain.exception.DomainException

open class StringVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : DomainException(code, message, cause)
