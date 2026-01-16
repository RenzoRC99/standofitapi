package com.standofit.standofitapi.workouttpl.domain.vo

import shared.domain.vo.BigIntegerVO
import java.math.BigInteger

class WorkoutExerciseRestTime(
    seconds: BigInteger
) : BigIntegerVO(seconds) {
    override fun validateType(value: BigInteger) {
        ensurePositive()
        ensureMin(BigInteger.ONE)
        ensureMax(BigInteger.valueOf(3600))
    }
}
