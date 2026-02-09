package shared.domain.voexceptions.bool

import shared.error.ErrorInfo

enum class BooleanVOMessages(
    override val code: String,
    override val message: String
) : ErrorInfo {
    MUST_BE_TRUE("BOOLEAN_MUST_BE_TRUE", "El valor debe ser verdadero"),
    MUST_BE_FALSE("BOOLEAN_MUST_BE_FALSE", "El valor debe ser falso"),
}
