package shared.domain.voexceptions.string

import shared.error.ErrorInfo

enum class StringVOMessages(
    override val code: String,
    override val message: String
) : ErrorInfo {
    EMPTY("STRING_EMPTY", "El string no puede estar vacío"),
    BLANK("STRING_BLANK", "El string no puede estar en blanco"),
    TOO_SHORT("STRING_TOO_SHORT", "El string es demasiado corto"),
    TOO_LONG("STRING_TOO_LONG", "El string es demasiado largo"),
    INVALID_CHARACTERS("STRING_INVALID_CHARACTERS", "El string contiene caracteres inválidos"),
    INVALID_FORMAT("STRING_INVALID_FORMAT", "El formato del string es inválido"),
    INVALID_EMOJI("STRING_INVALID_EMOJI", "El string contiene emojis no permitidos"),
    INVALID_PREFIX("STRING_INVALID_PREFIX", "El string no tiene el prefijo requerido"),
    INVALID_SUFFIX("STRING_INVALID_SUFFIX", "El string no tiene el sufijo requerido"),
    NOT_ALLOWED("STRING_NOT_ALLOWED", "El valor especificado no está permitido");
}
