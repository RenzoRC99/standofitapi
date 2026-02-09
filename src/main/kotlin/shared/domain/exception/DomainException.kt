package shared.domain.exception

import shared.error.BaseError


open class DomainException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : BaseError(code, message, cause)
