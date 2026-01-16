package com.standofit.standofitapi.workouttpl.domain.vo

import shared.domain.vo.StringVO

class WorkoutTplName(
    value: String
) : StringVO(value) {
    override fun validateType(value: String) {
        ensureNotBlank()
        ensureMinLength(3)
        ensureMaxLength(100)
        ensureNoEmojis()
    }
}
