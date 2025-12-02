package com.standofit.standofitapi.workouttpl.domain.vo

import shared.domain.vo.DateVO
import java.time.LocalDate

class WorkoutTplUpdatedAt(value: LocalDate) : DateVO(value) {
    override fun validateType(value: LocalDate) {
        TODO("Not yet implemented")
    }

}
