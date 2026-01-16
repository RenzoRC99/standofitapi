package com.standofit.standofitapi.workouttpl.domain.vo

import shared.domain.vo.StringVO

class WorkoutTplLevel(
    value: String
) : StringVO(value) {
    override fun validateType(value: String) {
        ensureNotBlank()
        ensureAllowedCharacters(Regex("^[A-Z]+$"))
        ensureMaxLength(20)
    }
}
