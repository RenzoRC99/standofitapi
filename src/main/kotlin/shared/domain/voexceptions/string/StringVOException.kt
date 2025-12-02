package shared.domain.voexceptions.string

import shared.domain.voexceptions.ValueObjectException

open class StringVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : ValueObjectException(code, message, cause)
