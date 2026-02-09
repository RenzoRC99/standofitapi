package com.standofit.standofitapi.workouttpl.domain.exception

import shared.domain.exception.DomainException

class WorkoutDayException(
    error: WorkoutDayErrors
) : DomainException(code = error.code, message = error.message)
