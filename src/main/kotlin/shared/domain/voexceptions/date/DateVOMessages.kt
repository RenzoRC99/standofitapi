package shared.domain.voexceptions.date

import shared.error.ErrorInfo

enum class DateVOMessages(
    override val code: String,
    override val message: String
) : ErrorInfo {
    IN_PAST("DATE_IN_PAST", "La fecha no puede estar en el pasado"),
    IN_FUTURE("DATE_IN_FUTURE", "La fecha no puede estar en el futuro"),
    BEFORE_MIN("DATE_BEFORE_MIN", "La fecha es anterior al mínimo permitido"),
    AFTER_MAX("DATE_AFTER_MAX", "La fecha es posterior al máximo permitido"),
    NOT_IN_RANGE("DATE_NOT_IN_RANGE", "La fecha no se encuentra en el rango permitido");
}
