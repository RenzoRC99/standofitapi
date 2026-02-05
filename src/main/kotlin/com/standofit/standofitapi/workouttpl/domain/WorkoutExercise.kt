package com.standofit.standofitapi.workouttpl.domain

import com.standofit.standofitapi.workouttpl.domain.vo.*

class WorkoutExercise private constructor(
    val id: WorkoutExerciseId,
    val sets: WorkoutExerciseSets,
    val reps: WorkoutExerciseReps,
    val restTime: WorkoutExerciseRestTime,
    val dropset: WorkoutExerciseDropset
) {
    companion object {
        fun create(
            id: WorkoutExerciseId,
            sets: WorkoutExerciseSets,
            reps: WorkoutExerciseReps,
            restTime: WorkoutExerciseRestTime,
            dropset: WorkoutExerciseDropset
        ): WorkoutExercise {
            return WorkoutExercise(
                id = id,
                sets = sets,
                reps = reps,
                restTime = restTime,
                dropset = dropset
            )
        }
    }

    fun changeSets(newSets: WorkoutExerciseSets): WorkoutExercise =
        copyWith(sets = newSets)

    fun changeReps(newReps: WorkoutExerciseReps): WorkoutExercise =
        copyWith(reps = newReps)

    fun changeRestTime(newRestTime: WorkoutExerciseRestTime): WorkoutExercise =
        copyWith(restTime = newRestTime)

    fun toggleDropset(): WorkoutExercise =
        copyWith(dropset = WorkoutExerciseDropset(!dropset.value))

    private fun copyWith(
        sets: WorkoutExerciseSets = this.sets,
        reps: WorkoutExerciseReps = this.reps,
        restTime: WorkoutExerciseRestTime = this.restTime,
        dropset: WorkoutExerciseDropset = this.dropset
    ): WorkoutExercise =
        create(id, sets, reps, restTime, dropset)
}
