package com.standofit.standofitapi.workouttpl.domain

import com.standofit.standofitapi.workouttpl.domain.exercise.ExerciseId
import com.standofit.standofitapi.workouttpl.domain.vo.*

class WorkoutExercise private constructor(
    val id: WorkoutExerciseId,
    val exerciseId: ExerciseId,
    val sets: WorkoutExerciseSets,
    val reps: WorkoutExerciseReps,
    val restTime: WorkoutExerciseRestTime,
    val dropset: WorkoutExerciseDropset
) {
    companion object {
        fun create(
            id: WorkoutExerciseId,
            exerciseId: ExerciseId,
            sets: WorkoutExerciseSets,
            reps: WorkoutExerciseReps,
            restTime: WorkoutExerciseRestTime,
            dropset: WorkoutExerciseDropset
        ): WorkoutExercise {
            return WorkoutExercise(
                id = id,
                exerciseId = exerciseId,
                sets = sets,
                reps = reps,
                restTime = restTime,
                dropset = dropset
            )
        }
    }
}
