package com.standofit.standofitapi.workouttpl.domain.exception

import shared.domain.exception.DomainException

class WorkoutTplException(
    error: WorkoutTplErrors
) : DomainException(code = error.code, message = error.message)
