package com.standofit.standofitapi.workouttpl.domain

import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutTplErrors
import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutTplException
import com.standofit.standofitapi.workouttpl.domain.vo.*


class WorkoutTpl private constructor(
    val id: WorkoutTplId,
    val name: WorkoutTplName,
    val description: WorkoutTplDescription,
    val level: WorkoutTplLevel,
    val status: WorkoutTplStatus,
    val createdAt: WorkoutTplCreatedAt,
    val updatedAt: WorkoutTplUpdatedAt,
    val days: List<WorkoutDay>
) {
    companion object {
        fun create(
            id: WorkoutTplId,
            name: WorkoutTplName,
            description: WorkoutTplDescription,
            level: WorkoutTplLevel,
            status: WorkoutTplStatus = WorkoutTplStatus.DRAFT,
            createdAt: WorkoutTplCreatedAt,
            updatedAt: WorkoutTplUpdatedAt,
            days: List<WorkoutDay> = emptyList()
        ): WorkoutTpl {
            return WorkoutTpl(
                id,
                name,
                description,
                level,
                status,
                createdAt,
                updatedAt,
                days
            )
        }
    }

    fun changeName(
        newName: WorkoutTplName,
        updatedAt: WorkoutTplUpdatedAt
    ): WorkoutTpl =
        copy(name = newName, updatedAt = updatedAt)

    fun changeDescription(
        newDescription: WorkoutTplDescription,
        updatedAt: WorkoutTplUpdatedAt
    ): WorkoutTpl =
        copy(description = newDescription, updatedAt = updatedAt)

    fun addDay(
        day: WorkoutDay,
        updatedAt: WorkoutTplUpdatedAt
    ): WorkoutTpl {
        checkDraft()
        if (days.any { it.day == day.day }) {
            throw WorkoutTplException(WorkoutTplErrors.DAY_ALREADY_EXISTS)
        }
        return copy(days = days + day, updatedAt = updatedAt)
    }

    fun removeDay(
        dayNumber: WorkoutDayNumber,
        updatedAt: WorkoutTplUpdatedAt
    ): WorkoutTpl {
        checkDraft()
        return copy(
            days = days.filter { it.day != dayNumber },
            updatedAt = updatedAt
        )
    }

    fun addExercise(
        dayNumber: WorkoutDayNumber,
        exercise: WorkoutExercise,
        updatedAt: WorkoutTplUpdatedAt
    ): WorkoutTpl {
        checkDraft()
        val day = findDay(dayNumber)
        return updateDay(day.addExercise(exercise), updatedAt)
    }

    fun removeExercise(
        dayNumber: WorkoutDayNumber,
        exerciseId: WorkoutExerciseId,
        updatedAt: WorkoutTplUpdatedAt
    ): WorkoutTpl {
        checkDraft()
        val day = findDay(dayNumber)
        return updateDay(day.removeExercise(exerciseId), updatedAt)
    }

    fun changeExercise(
        dayNumber: WorkoutDayNumber,
        exercise: WorkoutExercise,
        updatedAt: WorkoutTplUpdatedAt
    ): WorkoutTpl {
        checkDraft()
        val day = findDay(dayNumber)
        return updateDay(day.changeExercise(exercise), updatedAt)
    }

    fun publish(updatedAt: WorkoutTplUpdatedAt): WorkoutTpl {
        if (status != WorkoutTplStatus.DRAFT) {
            throw WorkoutTplException(WorkoutTplErrors.INVALID_STATUS_FOR_PUBLISH)
        }
        if (days.isEmpty()) {
            throw WorkoutTplException(WorkoutTplErrors.CANNOT_PUBLISH_EMPTY)
        }
        return copy(status = WorkoutTplStatus.PUBLISHED, updatedAt = updatedAt)
    }

    fun archive(updatedAt: WorkoutTplUpdatedAt): WorkoutTpl {
        if (status == WorkoutTplStatus.ARCHIVED) {
            throw WorkoutTplException(WorkoutTplErrors.ALREADY_ARCHIVED)
        }
        return copy(status = WorkoutTplStatus.ARCHIVED, updatedAt = updatedAt)
    }

    private fun copy(
        updatedAt: WorkoutTplUpdatedAt,
        name: WorkoutTplName = this.name,
        description: WorkoutTplDescription = this.description,
        level: WorkoutTplLevel = this.level,
        status: WorkoutTplStatus = this.status,
        days: List<WorkoutDay> = this.days
    ): WorkoutTpl =
        create(id, name, description, level, status, createdAt, updatedAt, days)

    private fun updateDay(
        updatedDay: WorkoutDay,
        updatedAt: WorkoutTplUpdatedAt
    ): WorkoutTpl {
        val newDays = days.map {
            if (it.day == updatedDay.day) updatedDay else it
        }
        return copy(days = newDays, updatedAt = updatedAt)
    }

    private fun findDay(dayNumber: WorkoutDayNumber): WorkoutDay =
        days.find { it.day == dayNumber }
            ?: throw WorkoutTplException(WorkoutTplErrors.DAY_NOT_FOUND)

    private fun checkDraft() {
        if (status != WorkoutTplStatus.DRAFT) {
            throw WorkoutTplException(WorkoutTplErrors.INVALID_STATUS_FOR_MODIFY)
        }
    }
}
