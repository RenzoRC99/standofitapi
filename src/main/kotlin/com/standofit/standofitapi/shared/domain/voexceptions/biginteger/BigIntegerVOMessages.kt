package com.standofit.standofitapi.shared.domain.voexceptions.biginteger

import com.standofit.standofitapi.shared.error.ErrorInfo

enum class BigIntegerVOMessages(
    override val code: String,
    override val message: String
) : ErrorInfo {
    NOT_POSITIVE("BIGINT_NOT_POSITIVE", "El número debe ser positivo"),
    NOT_NEGATIVE("BIGINT_NOT_NEGATIVE", "El número debe ser negativo"),
    TOO_SMALL("BIGINT_TOO_SMALL", "El número es demasiado pequeño"),
    TOO_LARGE("BIGINT_TOO_LARGE", "El número es demasiado grande"),
    ZERO_NOT_ALLOWED("BIGINT_ZERO_NOT_ALLOWED", "El número no puede ser cero");
}
