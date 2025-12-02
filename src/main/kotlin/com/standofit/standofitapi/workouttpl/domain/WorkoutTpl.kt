package com.standofit.standofitapi.workouttpl.domain

import WorkoutDay
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

    fun changeName(newName: WorkoutTplName): WorkoutTpl {
        return create(
            id, newName, description, level, status, createdAt, updatedAt, days
        )
    }

    fun changeDescription(newDescription: WorkoutTplDescription): WorkoutTpl {
        return create(id, name, newDescription, level, status, createdAt, updatedAt, days)
    }

    fun addDay(day: WorkoutDay): WorkoutTpl {
        checkDraft()
        if (days.any { it.day == day.day }) {
            throw WorkoutTplException(WorkoutTplErrors.DAY_ALREADY_EXISTS)
        }
        return create(id, name, description, level, status, createdAt, updatedAt, days + day)
    }

    fun removeDay(dayNumber: WorkoutDayNumber): WorkoutTpl {
        checkDraft()
        return WorkoutTpl.create(
            id,
            name,
            description,
            level,
            status,
            createdAt,
            updatedAt,
            days.filter { it.day != dayNumber })
    }

    fun addExercise(dayNumber: WorkoutDayNumber, exercise: WorkoutExercise): WorkoutTpl {
        checkDraft()
        val day = days.find { it.day == dayNumber } ?: throw WorkoutTplException(WorkoutTplErrors.DAY_NOT_FOUND)
        val updatedDay = day.addExercise(exercise)
        return updateDay(updatedDay)
    }

    fun removeExercise(dayNumber: WorkoutDayNumber, exerciseId: WorkoutExerciseId): WorkoutTpl {
        checkDraft()
        val day = days.find { it.day == dayNumber } ?: throw WorkoutTplException(WorkoutTplErrors.DAY_NOT_FOUND)
        val updatedDay = day.removeExercise(exerciseId)
        return updateDay(updatedDay)
    }

    fun changeExercise(dayNumber: WorkoutDayNumber, exercise: WorkoutExercise): WorkoutTpl {
        checkDraft()
        val day = days.find { it.day == dayNumber } ?: throw WorkoutTplException(WorkoutTplErrors.DAY_NOT_FOUND)
        val updatedDay = day.changeExercise(exercise)
        return updateDay(updatedDay)
    }

    fun publish(): WorkoutTpl {
        if (status != WorkoutTplStatus.DRAFT) {
            throw WorkoutTplException(WorkoutTplErrors.INVALID_STATUS_FOR_PUBLISH)
        }
        if (days.isEmpty()) {
            throw WorkoutTplException(WorkoutTplErrors.CANNOT_PUBLISH_EMPTY)
        }
        return WorkoutTpl.create(id, name, description, level, WorkoutTplStatus.PUBLISHED, createdAt, updatedAt, days)
    }

    fun archive(): WorkoutTpl {
        if (status == WorkoutTplStatus.ARCHIVED) {
            throw WorkoutTplException(WorkoutTplErrors.ALREADY_ARCHIVED)
        }
        return WorkoutTpl.create(id, name, description, level, WorkoutTplStatus.ARCHIVED, createdAt, updatedAt, days)
    }

    private fun updateDay(updatedDay: WorkoutDay): WorkoutTpl {
        val newDays = days.map { if (it.day == updatedDay.day) updatedDay else it }
        return WorkoutTpl.create(id, name, description, level, status, createdAt, updatedAt, newDays)
    }

    private fun checkDraft() {
        if (status != WorkoutTplStatus.DRAFT) {
            throw WorkoutTplException(WorkoutTplErrors.INVALID_STATUS_FOR_MODIFY)
        }
    }
}
