package com.standofit.standofitapi.workouttpl.domain.vo

import shared.domain.vo.DateVO
import java.time.Instant

class WorkoutTplUpdatedAt(
    value: Instant
) : DateVO(value) {
    override fun validateType(value: Instant) {
        ensureNotInFuture()
        ensureNotInPast()
    }

    companion object {
        fun now(): WorkoutTplUpdatedAt =
            WorkoutTplUpdatedAt(Instant.now())
    }
}
