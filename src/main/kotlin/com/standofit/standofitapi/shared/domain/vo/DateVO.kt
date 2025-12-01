package com.standofit.standofitapi.shared.domain.vo

import com.standofit.standofitapi.shared.domain.voexceptions.date.DateVOMessages
import com.standofit.standofitapi.shared.domain.voexceptions.date.DateVOException
import java.time.LocalDate

abstract class DateVO(
    value: LocalDate
) : BaseVO<LocalDate>(value) {

    protected fun ensureNotInPast() {
        if (value.isBefore(LocalDate.now())) {
            throw DateVOException(message = DateVOMessages.IN_PAST.message)
        }
    }

    protected fun ensureNotInFuture() {
        if (value.isAfter(LocalDate.now())) {
            throw DateVOException(message = DateVOMessages.IN_FUTURE.message)
        }
    }

    protected fun ensureAfter(min: LocalDate) {
        if (value.isBefore(min)) {
            throw DateVOException(message = DateVOMessages.BEFORE_MIN.message)
        }
    }

    protected fun ensureBefore(max: LocalDate) {
        if (value.isAfter(max)) {
            throw DateVOException(message = DateVOMessages.AFTER_MAX.message)
        }
    }

    protected fun ensureBetween(min: LocalDate, max: LocalDate) {
        if (value.isBefore(min) || value.isAfter(max)) {
            throw DateVOException(message = DateVOMessages.NOT_IN_RANGE.message)
        }
    }
}
