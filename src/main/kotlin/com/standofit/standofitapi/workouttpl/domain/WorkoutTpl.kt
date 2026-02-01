package com.standofit.standofitapi.workouttpl.domain

import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutTplErrors
import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutTplException
import com.standofit.standofitapi.workouttpl.domain.vo.*
import java.time.Instant


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
            days: List<WorkoutDay> = emptyList()
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

    fun changeName(newName: WorkoutTplName): WorkoutTpl =
        withUpdated { updateEntity(name = newName, updatedAt = it) }


    fun changeDescription(newDescription: WorkoutTplDescription): WorkoutTpl =
        withUpdated { updateEntity(description = newDescription, updatedAt = it) }

    fun addDay(day: WorkoutDay): WorkoutTpl =
        withUpdated { it ->
            checkDraft()
            if (days.any { it.day == day.day }) throw WorkoutTplException(WorkoutTplErrors.DAY_ALREADY_EXISTS)
            updateEntity(days = days + day, updatedAt = it)
        }

    fun removeDay(dayNumber: WorkoutDayNumber): WorkoutTpl =
        withUpdated { it ->
            checkDraft()
            updateEntity(days = days.filter { it.day != dayNumber }, updatedAt = it)
        }

    fun addExercise(dayNumber: WorkoutDayNumber, exercise: WorkoutExercise): WorkoutTpl =
        withUpdated {
            checkDraft()
            updateDay(findDay(dayNumber).addExercise(exercise), updatedAt = it)
        }

    fun removeExercise(dayNumber: WorkoutDayNumber, exerciseId: WorkoutExerciseId): WorkoutTpl =
        withUpdated {
            checkDraft()
            updateDay(findDay(dayNumber).removeExercise(exerciseId), updatedAt = it)
        }

    fun changeExercise(dayNumber: WorkoutDayNumber, exercise: WorkoutExercise): WorkoutTpl =
        withUpdated {
            checkDraft()
            updateDay(findDay(dayNumber).changeExercise(exercise), updatedAt = it)
        }

    fun publish(): WorkoutTpl =
        withUpdated {
            if (status != WorkoutTplStatus.DRAFT) throw WorkoutTplException(WorkoutTplErrors.INVALID_STATUS_FOR_PUBLISH)
            if (days.isEmpty()) throw WorkoutTplException(WorkoutTplErrors.CANNOT_PUBLISH_EMPTY)
            updateEntity(status = WorkoutTplStatus.PUBLISHED, updatedAt = it)
        }

    fun archive(): WorkoutTpl =
        withUpdated {
            if (status == WorkoutTplStatus.ARCHIVED) throw WorkoutTplException(WorkoutTplErrors.ALREADY_ARCHIVED)
            updateEntity(status = WorkoutTplStatus.ARCHIVED, updatedAt = it)
        }

    private fun updateDay(updatedDay: WorkoutDay, updatedAt: WorkoutTplUpdatedAt): WorkoutTpl {
        val newDays = days.map { if (it.day == updatedDay.day) updatedDay else it }
        return updateEntity(days = newDays, updatedAt = updatedAt)
    }

    private fun findDay(dayNumber: WorkoutDayNumber): WorkoutDay =
        days.find { it.day == dayNumber } ?: throw WorkoutTplException(WorkoutTplErrors.DAY_NOT_FOUND)

    private fun checkDraft() {
        if (status != WorkoutTplStatus.DRAFT) {
            throw WorkoutTplException(WorkoutTplErrors.INVALID_STATUS_FOR_MODIFY)
        }
    }

    private fun updateEntity(
        updatedAt: WorkoutTplUpdatedAt,
        name: WorkoutTplName = this.name,
        description: WorkoutTplDescription = this.description,
        level: WorkoutTplLevel = this.level,
        status: WorkoutTplStatus = this.status,
        days: List<WorkoutDay> = this.days
    ): WorkoutTpl =
        WorkoutTpl(id, name, description, level, status, createdAt, updatedAt, days)

    private fun withUpdated(updateAction: (WorkoutTplUpdatedAt) -> WorkoutTpl): WorkoutTpl {
        val updatedAt = WorkoutTplUpdatedAt.now()
        validateUpdatedAt(updatedAt)
        return updateAction(updatedAt)
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
