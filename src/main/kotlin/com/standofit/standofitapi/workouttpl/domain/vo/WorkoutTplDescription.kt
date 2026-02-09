package com.standofit.standofitapi.workouttpl.domain.vo

import shared.domain.vo.StringVO

class WorkoutTplDescription(
    value: String
) : StringVO(value) {
    override fun validateType(value: String) {
        if (value.isNotEmpty()) {
            ensureMaxLength(500)
            ensureNoEmojis()
        }
    }
}
