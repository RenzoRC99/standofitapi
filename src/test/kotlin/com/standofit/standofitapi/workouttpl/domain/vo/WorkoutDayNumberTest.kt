package com.standofit.standofitapi.workouttpl.domain.vo


import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import shared.domain.voexceptions.biginteger.BigIntegerVOException
import java.math.BigInteger

class WorkoutDayNumberTest {
    @Test
    fun `should create workout day number when value is between 1 and 6000`() {
        assertDoesNotThrow {
            WorkoutDayNumber(BigInteger.ONE)
            WorkoutDayNumber(BigInteger.valueOf(3000))
            WorkoutDayNumber(BigInteger.valueOf(6000))
        }
    }

    @Test
    fun `should throw exception when value is zero`() {
        assertThrows<BigIntegerVOException> {
            WorkoutDayNumber(BigInteger.ZERO)
        }
    }

    @Test
    fun `should throw exception when value is negative`() {
        assertThrows<BigIntegerVOException> {
            WorkoutDayNumber(BigInteger.valueOf(-1))
        }
    }

    @Test
    fun `should throw exception when value is greater than 6000`() {
        assertThrows<BigIntegerVOException> {
            WorkoutDayNumber(BigInteger.valueOf(6001))
        }
    }
}