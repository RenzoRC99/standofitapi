package com.standofit.standofitapi.workouttpl.domain.vo

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import shared.domain.voexceptions.uuid.UUIDVOException
import java.util.*
import kotlin.test.Test


class WorkoutDayIdTest {
    @Test
    fun `valid UUID should not throw`() {
        val id = UUID.randomUUID() // version 4, variant 2
        assertDoesNotThrow { WorkoutDayId(id) }
    }

    @Test
    fun `invalid version should throw`() {
        val invalidUuid = UUID(0L, 0L) // version 0
        assertThrows<UUIDVOException> { WorkoutDayId(invalidUuid) }
    }

    @Test
    fun `invalid variant should throw`() {
        val msb = 0L
        val lsb = 0L
        val invalidUuid = UUID(msb, lsb) // variant 0
        assertThrows<UUIDVOException> { WorkoutDayId(invalidUuid) }
    }
}