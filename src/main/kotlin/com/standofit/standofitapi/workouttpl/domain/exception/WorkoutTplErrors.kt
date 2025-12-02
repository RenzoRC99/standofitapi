package com.standofit.standofitapi.workouttpl.domain.exception

import shared.error.ErrorInfo

enum class WorkoutTplErrors(
    override val code: String,
    override val message: String
) : ErrorInfo {
    DAY_ALREADY_EXISTS("WTPL_001", "Day already exists in this template"),
    DAY_NOT_FOUND("WTPL_002", "Day not found in this template"),
    EXERCISE_ALREADY_EXISTS("WTPL_003", "Exercise already exists in this day"),
    EXERCISE_NOT_FOUND("WTPL_004", "Exercise not found in this day"),
    CANNOT_PUBLISH_EMPTY("WTPL_005", "Cannot publish a template without days"),
    INVALID_STATUS_FOR_PUBLISH("WTPL_006", "Only draft templates can be published"),
    ALREADY_ARCHIVED("WTPL_007", "Template is already archived"),
    INVALID_STATUS_FOR_MODIFY("WTPL_008", "Only draft templates can be modified")
}
