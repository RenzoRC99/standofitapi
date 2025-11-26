package com.standofit.standofitapi.shared.domain.exception.voexceptions.biginteger

import com.standofit.standofitapi.shared.domain.exception.DomainException

open class BigIntegerVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : DomainException(code, message, cause)
