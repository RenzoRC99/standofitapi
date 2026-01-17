package com.standofit.standofitapi.workouttpl.domain.exercise

import shared.domain.vo.UUIDVO
import java.util.*

class ExerciseId(
    value: UUID

) : UUIDVO(value) {
    override fun validateType(value: UUID) {
        ensureVersion(4)
        ensureVariant(2)
    }
}
