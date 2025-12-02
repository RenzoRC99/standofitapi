package shared.domain.voexceptions.biginteger

import shared.domain.voexceptions.ValueObjectException


open class BigIntegerVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : ValueObjectException(code, message, cause)
