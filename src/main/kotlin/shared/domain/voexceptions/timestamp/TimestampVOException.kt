package shared.domain.voexceptions.timestamp

import shared.domain.voexceptions.ValueObjectException

open class TimestampVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : ValueObjectException(code, message, cause)
