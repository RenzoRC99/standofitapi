package com.standofit.standofitapi.shared.domain.voexceptions.date

import com.standofit.standofitapi.shared.domain.voexceptions.ValueObjectException

open class DateVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : ValueObjectException(code, message, cause)
