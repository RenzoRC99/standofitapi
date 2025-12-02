package shared.domain.voexceptions.bool

import shared.domain.voexceptions.ValueObjectException

open class BooleanVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : ValueObjectException(code, message, cause)
