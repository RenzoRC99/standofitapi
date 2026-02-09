package com.standofit.standofitapi.workouttpl.domain.vo

import shared.domain.vo.BigIntegerVO
import java.math.BigInteger

class WorkoutExerciseReps(
    value: BigInteger
) : BigIntegerVO(value) {
    override fun validateType(value: BigInteger) {
        ensurePositive()
        ensureMin(BigInteger.ONE)
        ensureMax(BigInteger.valueOf(500))
    }
}
