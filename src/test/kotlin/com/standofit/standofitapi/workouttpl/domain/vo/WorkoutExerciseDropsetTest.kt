package com.standofit.standofitapi.workouttpl.domain.vo

import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

class WorkoutExerciseDropsetTest {
    @Test
    fun `should allow true value`() {
        assertDoesNotThrow { WorkoutExerciseDropset(true) }
    }

    @Test
    fun `should allow false value`() {
        assertDoesNotThrow { WorkoutExerciseDropset(false) }
    }
}