package com.standofit.standofitapi.workouttpl.domain

import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutTplErrors
import com.standofit.standofitapi.workouttpl.domain.exception.WorkoutTplException
import com.standofit.standofitapi.workouttpl.domain.vo.WorkoutDayNumber

class WorkoutDays private constructor(
    private val items: List<WorkoutDay>
) {

    companion object {
        fun create(days: List<WorkoutDay> = emptyList()): WorkoutDays {
            validateNoDuplicates(days)
            return WorkoutDays(days.sortedBy { it.day.value })
        }

        private fun validateNoDuplicates(days: List<WorkoutDay>) {
            if (days.groupBy { it.day.value }.any { it.value.size > 1 }) {
                throw WorkoutTplException(WorkoutTplErrors.DAY_ALREADY_EXISTS)
            }
        }
    }

    fun add(day: WorkoutDay): WorkoutDays {
        if (contains(day.day)) {
            throw WorkoutTplException(WorkoutTplErrors.DAY_ALREADY_EXISTS)
        }
        return create(items + day)
    }

    fun remove(dayNumber: WorkoutDayNumber): WorkoutDays {
        if (!contains(dayNumber)) {
            throw WorkoutTplException(WorkoutTplErrors.DAY_NOT_FOUND)
        }
        return create(items.filterNot { it.day == dayNumber })
    }

    fun update(day: WorkoutDay): WorkoutDays =
        create(items.map { if (it.day == day.day) day else it })

    fun find(dayNumber: WorkoutDayNumber): WorkoutDay =
        items.find { it.day == dayNumber }
            ?: throw WorkoutTplException(WorkoutTplErrors.DAY_NOT_FOUND)

    fun toList(): List<WorkoutDay> = items.toList()

    fun size() = items.size

    fun isEmpty(): Boolean = items.isEmpty()

    private fun contains(dayNumber: WorkoutDayNumber) =
        items.any { it.day == dayNumber }
}
