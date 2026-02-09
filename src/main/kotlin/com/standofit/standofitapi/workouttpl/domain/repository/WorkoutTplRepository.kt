package com.standofit.standofitapi.workouttpl.domain.repository

import com.standofit.standofitapi.workouttpl.domain.WorkoutTpl
import com.standofit.standofitapi.workouttpl.domain.vo.WorkoutTplId

interface WorkoutTplRepository {

    fun persistWorkoutTpl(workoutTpl: WorkoutTpl)

    fun fetchWorkoutTplById(id: WorkoutTplId): WorkoutTpl?

    fun removeWorkoutTpl(workoutTpl: WorkoutTpl)

    fun fetchAllWorkoutTpls(): List<WorkoutTpl>
}
