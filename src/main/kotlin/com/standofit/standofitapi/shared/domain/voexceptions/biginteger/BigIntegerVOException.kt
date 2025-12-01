package com.standofit.standofitapi.shared.domain.voexceptions.biginteger

import com.standofit.standofitapi.shared.domain.voexceptions.ValueObjectException

open class BigIntegerVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : ValueObjectException(code, message, cause)
