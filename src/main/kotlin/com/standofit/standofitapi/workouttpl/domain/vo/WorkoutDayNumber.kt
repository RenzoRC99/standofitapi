package com.standofit.standofitapi.workouttpl.domain.vo

import shared.domain.vo.BigIntegerVO
import java.math.BigInteger

class WorkoutDayNumber(
    value: BigInteger
) : BigIntegerVO(value) {
    override fun validateType(value: BigInteger) {
        ensureMax(BigInteger.valueOf(6000))
        ensureMin(BigInteger.ONE)
    }
}
