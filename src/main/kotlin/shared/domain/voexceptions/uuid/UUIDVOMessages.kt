package shared.domain.voexceptions.uuid

import shared.error.ErrorInfo

enum class UUIDVOMessages(
    override val code: String,
    override val message: String
) : ErrorInfo {
    INVALID_VERSION("UUID_INVALID_VERSION", "El UUID no tiene la versión requerida"),
    INVALID_VARIANT("UUID_INVALID_VARIANT", "El UUID no tiene la variante correcta");
}
