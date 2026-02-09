package com.standofit.standofitapi.workouttpl.domain

import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutTplErrors
import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutTplException
import com.standofit.standofitapi.workouttpl.domain.vo.*
import shared.domain.agregate.AggregateRoot
import java.time.Instant


class WorkoutTpl private constructor(
    val id: WorkoutTplId,
    val name: WorkoutTplName,
    val description: WorkoutTplDescription,
    val level: WorkoutTplLevel,
    val status: WorkoutTplStatus,
    val createdAt: WorkoutTplCreatedAt,
    val updatedAt: WorkoutTplUpdatedAt,
    private val days: WorkoutDays
) : AggregateRoot() {
    companion object {
        fun create(
            id: WorkoutTplId,
            name: WorkoutTplName,
            description: WorkoutTplDescription,
            level: WorkoutTplLevel,
            status: WorkoutTplStatus = WorkoutTplStatus.DRAFT,
            days: WorkoutDays = WorkoutDays.create()
        ): WorkoutTpl {
            return WorkoutTpl(
                id,
                name,
                description,
                level,
                status,
                createdAt = WorkoutTplCreatedAt.now(),
                updatedAt = WorkoutTplUpdatedAt.now(),
                days
            )
        }
    }

    fun changeName(newName: WorkoutTplName): WorkoutTpl {
        assertDraft()
        val updatedAt = nextUpdatedAt()
        return copyWith(
            name = newName,
            updatedAt = updatedAt
        )
    }

    fun changeDescription(newDescription: WorkoutTplDescription): WorkoutTpl {
        assertDraft()
        val updatedAt = nextUpdatedAt()
        return copyWith(
            description = newDescription,
            updatedAt = updatedAt
        )
    }

    fun addDay(day: WorkoutDay): WorkoutTpl {
        assertDraft()
        val updatedDays = days.add(day)
        return copyWith(days = updatedDays, updatedAt = nextUpdatedAt())
    }

    fun removeDay(dayNumber: WorkoutDayNumber): WorkoutTpl {
        assertDraft()
        val updatedDays = days.remove(dayNumber)
        return copyWith(days = updatedDays, updatedAt = nextUpdatedAt())
    }

    fun addExerciseToDay(dayNumber: WorkoutDayNumber, exercise: WorkoutExercise): WorkoutTpl {
        assertDraft()
        val day = days.find(dayNumber).addExercise(exercise)
        val updatedDays = days.update(day)
        return copyWith(days = updatedDays, updatedAt = nextUpdatedAt())
    }

    fun removeExercise(dayNumber: WorkoutDayNumber, exerciseId: WorkoutExerciseId): WorkoutTpl {
        assertDraft()
        val day = days.find(dayNumber).removeExercise(exerciseId)
        val updatedDays = days.update(day)
        return copyWith(days = updatedDays, updatedAt = nextUpdatedAt())

    }

    fun replaceExerciseInDay(
        dayNumber: WorkoutDayNumber,
        oldExerciseId: WorkoutExerciseId,
        newExercise: WorkoutExercise
    ): WorkoutTpl {
        assertDraft()
        val day = days.find(dayNumber)
        val updatedDay = day.replaceExercise(oldExerciseId, newExercise)
        val updatedDays = days.update(updatedDay)
        return copyWith(days = updatedDays, updatedAt = nextUpdatedAt())
    }

    fun changeRepsInDay(
        dayNumber: WorkoutDayNumber,
        exerciseId: WorkoutExerciseId,
        reps: WorkoutExerciseReps
    ): WorkoutTpl {
        assertDraft()
        val day = days.find(dayNumber)
        val updatedDay = day.changeExerciseAttributes(exerciseId = exerciseId, reps = reps)
        return copyWith(days = days.update(updatedDay), updatedAt = nextUpdatedAt())
    }

    fun changeSetsInDay(
        dayNumber: WorkoutDayNumber,
        exerciseId: WorkoutExerciseId,
        sets: WorkoutExerciseSets
    ): WorkoutTpl {
        assertDraft()
        val day = days.find(dayNumber)
        val updatedDay = day.changeExerciseAttributes(exerciseId = exerciseId, sets = sets)
        return copyWith(days = days.update(updatedDay), updatedAt = nextUpdatedAt())
    }

    fun changeRestTimeInDay(
        dayNumber: WorkoutDayNumber,
        exerciseId: WorkoutExerciseId,
        restTime: WorkoutExerciseRestTime
    ): WorkoutTpl {
        assertDraft()
        val day = days.find(dayNumber)
        val updatedDay = day.changeExerciseAttributes(exerciseId = exerciseId, restTime = restTime)
        return copyWith(days = days.update(updatedDay), updatedAt = nextUpdatedAt())
    }

    fun toggleDropsetInDay(
        dayNumber: WorkoutDayNumber,
        exerciseId: WorkoutExerciseId,
        currentDropset: Boolean
    ): WorkoutTpl {
        assertDraft()
        val day = days.find(dayNumber)
        val updatedDay = day.changeExerciseAttributes(exerciseId = exerciseId, dropset = !currentDropset)
        return copyWith(days = days.update(updatedDay), updatedAt = nextUpdatedAt())
    }

    fun publish(): WorkoutTpl {
        assertCanBePublished()
        val updatedAt = nextUpdatedAt()
        return copyWith(
            status = WorkoutTplStatus.PUBLISHED,
            updatedAt = updatedAt
        )
    }

    fun archive(): WorkoutTpl {
        assertNotArchived()
        val updatedAt = nextUpdatedAt()
        return copyWith(
            status = WorkoutTplStatus.ARCHIVED,
            updatedAt = updatedAt
        )
    }

    private fun assertDraft() {
        if (status != WorkoutTplStatus.DRAFT) {
            throw WorkoutTplException(WorkoutTplErrors.INVALID_STATUS_FOR_MODIFY)
        }
    }

    private fun assertNotArchived() {
        if (status == WorkoutTplStatus.ARCHIVED) {
            throw WorkoutTplException(WorkoutTplErrors.ALREADY_ARCHIVED)
        }
    }

    private fun assertCanBePublished() {
        if (status != WorkoutTplStatus.DRAFT) {
            throw WorkoutTplException(WorkoutTplErrors.INVALID_STATUS_FOR_PUBLISH)
        }
        if (days.isEmpty()) {
            throw WorkoutTplException(WorkoutTplErrors.CANNOT_PUBLISH_EMPTY)
        }
    }

    private fun copyWith(
        updatedAt: WorkoutTplUpdatedAt,
        name: WorkoutTplName = this.name,
        description: WorkoutTplDescription = this.description,
        level: WorkoutTplLevel = this.level,
        status: WorkoutTplStatus = this.status,
        days: WorkoutDays = this.days
    ): WorkoutTpl =
        WorkoutTpl(id, name, description, level, status, createdAt, updatedAt, days)

    private fun nextUpdatedAt(): WorkoutTplUpdatedAt {
        val now = WorkoutTplUpdatedAt.now()
        validateUpdatedAt(now)
        return now
    }

    private fun validateUpdatedAt(newUpdatedAt: WorkoutTplUpdatedAt) {
        require(newUpdatedAt.value >= createdAt.value) {
            WorkoutTplErrors.INVALID_UPDATED_AT.message
        }
        require(newUpdatedAt.value >= updatedAt.value) {
            WorkoutTplErrors.UPDATED_AT_CANNOT_DECREASE.message
        }
        require(newUpdatedAt.value <= Instant.now()) {
            WorkoutTplErrors.UPDATED_AT_IN_FUTURE.message
        }
    }
}
