package shared.domain.voexceptions.timestamp

import shared.error.ErrorInfo

enum class TimestampVOMessages(
    override val code: String,
    override val message: String
) : ErrorInfo {
    IN_PAST("TIMESTAMP_IN_PAST", "El timestamp no puede estar en el pasado"),
    IN_FUTURE("TIMESTAMP_IN_FUTURE", "El timestamp no puede estar en el futuro"),
    BEFORE_MIN("TIMESTAMP_BEFORE_MIN", "El timestamp es anterior al mínimo permitido"),
    AFTER_MAX("TIMESTAMP_AFTER_MAX", "El timestamp es posterior al máximo permitido"),
    NOT_IN_RANGE("TIMESTAMP_NOT_IN_RANGE", "El timestamp no está en el rango permitido");
}
