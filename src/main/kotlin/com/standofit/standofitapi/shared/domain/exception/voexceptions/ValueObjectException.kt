package com.standofit.standofitapi.shared.domain.exception.voexceptions

import com.standofit.standofitapi.shared.domain.exception.DomainException

open class ValueObjectException(
    code: String? = null,         
    message: String,
    cause: Throwable? = null
) : DomainException(code, message, cause)
