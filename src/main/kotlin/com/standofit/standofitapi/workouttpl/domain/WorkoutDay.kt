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
            if (exercises.isEmpty()) {
                throw WorkoutDayException(WorkoutDayErrors.DAY_WITHOUT_EXERCISES)
            }
            return WorkoutDay(id, day, exercises)
        }
    }
    
    fun addExercise(exercise: WorkoutExercise): WorkoutDay {
        if (exercises.any { it.id == exercise.id }) {
            throw WorkoutDayException(WorkoutDayErrors.EXERCISE_ALREADY_EXISTS)
        }
        return create(id, day, exercises + exercise)
    }

    fun removeExercise(exerciseId: WorkoutExerciseId): WorkoutDay {
        if (exercises.none { it.id == exerciseId }) {
            throw WorkoutDayException(WorkoutDayErrors.EXERCISE_NOT_FOUND)
        }
        return create(id, day, exercises.filterNot { it.id == exerciseId })
    }

    fun changeExercise(exercise: WorkoutExercise): WorkoutDay {
        val index = exercises.indexOfFirst { it.id == exercise.id }
        if (index == -1) {
            throw WorkoutDayException(WorkoutDayErrors.EXERCISE_NOT_FOUND)
        }
        val newExercises = exercises.toMutableList()
        newExercises[index] = exercise
        return create(id, day, newExercises)
    }
}
