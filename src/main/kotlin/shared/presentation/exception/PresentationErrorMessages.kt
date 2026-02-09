package shared.presentation.exception

import shared.error.ErrorInfo

enum class PresentationErrorMessages(
    override val code: String,
    override val message: String
) : ErrorInfo {
    INVALID_REQUEST("INVALID_REQUEST", "La solicitud no es válida"),
    UNAUTHORIZED("UNAUTHORIZED", "No autorizado"),
    FORBIDDEN("FORBIDDEN", "Acceso denegado"),
    NOT_FOUND("NOT_FOUND", "Recurso no encontrado"),
    INTERNAL_ERROR("INTERNAL_ERROR", "Error interno del servidor")
}
