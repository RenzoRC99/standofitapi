package com.standofit.standofitapi.workouttpl.domain.vo

import shared.domain.vo.UUIDVO
import java.util.*

class WorkoutDayId(
    value: UUID
) : UUIDVO(value) {
    override fun validateType(value: UUID) {
        TODO("Not yet implemented")
    }

}
