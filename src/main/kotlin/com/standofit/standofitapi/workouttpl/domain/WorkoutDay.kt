package com.standofit.standofitapi.workouttpl.domain

import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutDayErrors
import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutDayException
import com.standofit.standofitapi.workouttpl.domain.vo.WorkoutDayId
import com.standofit.standofitapi.workouttpl.domain.vo.WorkoutDayNumber
import com.standofit.standofitapi.workouttpl.domain.vo.WorkoutExerciseId

class WorkoutDay private constructor(
    val id: WorkoutDayId,
    val day: WorkoutDayNumber,
    val exercises: List<WorkoutExercise>
) {
    companion object {
        fun create(
            id: WorkoutDayId,
            day: WorkoutDayNumber,
            exercises: List<WorkoutExercise> = emptyList()
        ): WorkoutDay {
            return WorkoutDay(id, day, exercises)
        }
    }

    fun addExercise(workoutExercise: WorkoutExercise): WorkoutDay {
        if (exercises.any { it.id == workoutExercise.id }) {
            throw WorkoutDayException(WorkoutDayErrors.EXERCISE_ALREADY_EXISTS)
        }
        return create(id, day, exercises + workoutExercise)
    }

    fun removeExercise(workoutExerciseId: WorkoutExerciseId): WorkoutDay {
        if (exercises.none { it.id == workoutExerciseId }) {
            throw WorkoutDayException(WorkoutDayErrors.EXERCISE_NOT_FOUND)
        }
        return create(id, day, exercises.filter { it.id != workoutExerciseId })
    }

    fun changeExercise(workoutExercise: WorkoutExercise): WorkoutDay {
        val index = exercises.indexOfFirst { it.id == workoutExercise.id }
        if (index == -1) {
            throw WorkoutDayException(WorkoutDayErrors.EXERCISE_NOT_FOUND)
        }
        val newExercises = exercises.toMutableList()
        newExercises[index] = workoutExercise
        return create(id, day, newExercises)
    }
}
