package com.standofit.standofitapi.shared.domain.voexceptions.bool

import com.standofit.standofitapi.shared.domain.voexceptions.ValueObjectException

open class BooleanVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : ValueObjectException(code, message, cause)
