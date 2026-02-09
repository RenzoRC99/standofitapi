package shared.infrastructure.exception

import shared.error.BaseError

open class InfrastructureException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : BaseError(code, message, cause)
