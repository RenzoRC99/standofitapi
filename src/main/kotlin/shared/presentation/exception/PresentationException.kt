package shared.presentation.exception

import shared.error.BaseError

open class PresentationException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : BaseError(code, message, cause)
