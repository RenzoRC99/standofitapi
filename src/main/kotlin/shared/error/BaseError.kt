package shared.error

open class BaseError(
    val code: String? = null,
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
