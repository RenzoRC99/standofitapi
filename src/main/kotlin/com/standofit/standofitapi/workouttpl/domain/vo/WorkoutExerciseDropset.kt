package com.standofit.standofitapi.workouttpl.domain.vo

import shared.domain.vo.BooleanVO

class WorkoutExerciseDropset(
    value: Boolean
) : BooleanVO(value) {
    override fun validateType(value: Boolean) {
    }
}
