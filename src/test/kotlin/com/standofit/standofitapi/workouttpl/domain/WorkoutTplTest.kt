package com.standofit.standofitapi.workouttpl.domain

import com.standofit.standofitapi.workouttpl.domain.dsl.WorkoutTplDsl
import com.standofit.standofitapi.workouttpl.domain.mothers.WorkoutDayMother
import com.standofit.standofitapi.workouttpl.domain.mothers.WorkoutTplMother
import com.standofit.standofitapi.workouttpl.domain.vo.WorkoutTplStatus
import org.junit.jupiter.api.Test


class WorkoutTplTest {

    @Test
    fun `add day to template`() {

        val day1 = WorkoutDayMother.withSingleExercise()
        val day2 = WorkoutDayMother.secondDay()

        val tpl = WorkoutTplMother.draftWithOneDay(day1)

        WorkoutTplDsl.given(tpl)
            .whenAddDay(day2)
            .thenDayCountIs(2)
    }

    @Test
    fun `publish template with days`() {

        val day = WorkoutDayMother.withSingleExercise()
        val tpl = WorkoutTplMother.draftWithOneDay(day)

        WorkoutTplDsl.given(tpl)
            .whenPublish()
            .thenStatusIs(WorkoutTplStatus.PUBLISHED)
    }
}
