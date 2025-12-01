package com.standofit.standofitapi.shared.domain.voexceptions.string

import com.standofit.standofitapi.shared.domain.voexceptions.ValueObjectException

open class StringVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : ValueObjectException(code, message, cause)
