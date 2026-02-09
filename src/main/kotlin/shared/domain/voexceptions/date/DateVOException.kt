package shared.domain.voexceptions.date

import shared.domain.voexceptions.ValueObjectException

open class DateVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : ValueObjectException(code, message, cause)
