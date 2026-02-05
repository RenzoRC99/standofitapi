package com.standofit.standofitapi.workouttpl.domain

import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutDayErrors
import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutDayException
import com.standofit.standofitapi.workouttpl.domain.vo.*

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
        val updatedExercises = exercises.filterNot { it.id == exerciseId }
        if (updatedExercises.isEmpty()) {
            throw WorkoutDayException(WorkoutDayErrors.DAY_WITHOUT_EXERCISES)
        }
        return create(id, day, updatedExercises)
    }

    fun replaceExercise(oldExerciseId: WorkoutExerciseId, newExercise: WorkoutExercise): WorkoutDay {
        val index = exercises.indexOfFirst { it.id == oldExerciseId }
        if (index == -1) throw WorkoutDayException(WorkoutDayErrors.EXERCISE_NOT_FOUND)

        val newExercises = exercises.toMutableList()
        newExercises[index] = newExercise

        return create(id, day, newExercises)
    }

    fun changeExerciseAttributes(
        exerciseId: WorkoutExerciseId,
        sets: WorkoutExerciseSets? = null,
        reps: WorkoutExerciseReps? = null,
        restTime: WorkoutExerciseRestTime? = null,
        dropset: Boolean? = null
    ): WorkoutDay {
        val index = exercises.indexOfFirst { it.id == exerciseId }
        if (index == -1) throw WorkoutDayException(WorkoutDayErrors.EXERCISE_NOT_FOUND)

        val oldExercise = exercises[index]

        var updatedExercise = oldExercise
        sets?.let { updatedExercise = updatedExercise.changeSets(it) }
        reps?.let { updatedExercise = updatedExercise.changeReps(it) }
        restTime?.let { updatedExercise = updatedExercise.changeRestTime(it) }
        dropset?.let { updatedExercise = updatedExercise.toggleDropset() }

        val newExercises = exercises.toMutableList()
        newExercises[index] = updatedExercise

        return create(id, day, newExercises)
    }
}
