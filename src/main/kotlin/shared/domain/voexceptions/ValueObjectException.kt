package shared.domain.voexceptions

import shared.domain.exception.DomainException

open class ValueObjectException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : DomainException(code, message, cause)
