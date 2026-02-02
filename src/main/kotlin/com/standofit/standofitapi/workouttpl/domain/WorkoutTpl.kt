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
        assertDayDoesNotExist(day.day)

        val updatedAt = nextUpdatedAt()
        return copyWith(
            days = days + day,
            updatedAt = updatedAt
        )
    }

    fun removeDay(dayNumber: WorkoutDayNumber): WorkoutTpl {
        assertDraft()
        assertDayExists(dayNumber)

        val updatedAt = nextUpdatedAt()
        return copyWith(
            days = days.filter { it.day != dayNumber },
            updatedAt = updatedAt
        )
    }

    fun addExercise(dayNumber: WorkoutDayNumber, exercise: WorkoutExercise): WorkoutTpl {
        assertDraft()
        val updatedAt = nextUpdatedAt()
        return updateDay(
            requireDay(dayNumber).addExercise(exercise),
            updatedAt
        )
    }

    fun removeExercise(dayNumber: WorkoutDayNumber, exerciseId: WorkoutExerciseId): WorkoutTpl {
        assertDraft()
        val updatedAt = nextUpdatedAt()
        return updateDay(
            requireDay(dayNumber).removeExercise(exerciseId),
            updatedAt
        )
    }

    fun changeExercise(dayNumber: WorkoutDayNumber, exercise: WorkoutExercise): WorkoutTpl {
        assertDraft()
        val updatedAt = nextUpdatedAt()
        return updateDay(
            requireDay(dayNumber).changeExercise(exercise),
            updatedAt
        )
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

    private fun updateDay(updatedDay: WorkoutDay, updatedAt: WorkoutTplUpdatedAt): WorkoutTpl {
        val newDays = days.map { if (it.day == updatedDay.day) updatedDay else it }
        return copyWith(days = newDays, updatedAt = updatedAt)
    }

    private fun requireDay(dayNumber: WorkoutDayNumber): WorkoutDay =
        days.find { it.day == dayNumber } ?: throw WorkoutTplException(WorkoutTplErrors.DAY_NOT_FOUND)

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
        days: List<WorkoutDay> = this.days
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

    private fun assertDayDoesNotExist(dayNumber: WorkoutDayNumber) {
        if (days.any { it.day == dayNumber }) {
            throw WorkoutTplException(WorkoutTplErrors.DAY_ALREADY_EXISTS)
        }
    }

    private fun assertDayExists(dayNumber: WorkoutDayNumber) {
        if (days.none { it.day == dayNumber }) {
            throw WorkoutTplException(WorkoutTplErrors.DAY_NOT_FOUND)
        }
    }
}
