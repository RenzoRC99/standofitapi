package com.standofit.standofitapi.shared.domain.voexceptions

import com.standofit.standofitapi.shared.domain.exception.DomainException

open class ValueObjectException(
    code: String? = null,         
    message: String,
    cause: Throwable? = null
) : DomainException(code, message, cause)
