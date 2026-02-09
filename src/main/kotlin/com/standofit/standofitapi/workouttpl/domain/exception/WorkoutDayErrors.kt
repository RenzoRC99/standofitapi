package com.standofit.standofitapi.workouttpl.domain.exception

import shared.error.ErrorInfo

enum class WorkoutDayErrors(
    override val code: String,
    override val message: String
) : ErrorInfo {
    EXERCISE_ALREADY_EXISTS("WDAY_001", "Exercise already exists in this day"),
    EXERCISE_NOT_FOUND("WDAY_002", "Exercise not found in this day"),
    DAY_WITHOUT_EXERCISES("WDAY_003", "A day must have at least one exercise")
}
