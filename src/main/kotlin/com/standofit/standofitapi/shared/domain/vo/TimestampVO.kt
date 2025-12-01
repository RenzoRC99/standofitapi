package com.standofit.standofitapi.shared.domain.vo

import com.standofit.standofitapi.shared.domain.voexceptions.timestamp.TimestampVOException
import com.standofit.standofitapi.shared.domain.voexceptions.timestamp.TimestampVOMessages
import java.time.Instant

abstract class TimestampVO(
    value: Instant
) : BaseVO<Instant>(value) {

    protected fun ensureNotInFuture() {
        if (value.isAfter(Instant.now())) {
            throw TimestampVOException(message = TimestampVOMessages.IN_FUTURE.message)
        }
    }

    protected fun ensureNotInPast() {
        if (value.isBefore(Instant.now())) {
            throw TimestampVOException(message = TimestampVOMessages.IN_PAST.message)
        }
    }

    protected fun ensureAfter(min: Instant) {
        if (value.isBefore(min)) {
            throw TimestampVOException(message = TimestampVOMessages.BEFORE_MIN.message)
        }
    }

    protected fun ensureBefore(max: Instant) {
        if (value.isAfter(max)) {
            throw TimestampVOException(message = TimestampVOMessages.AFTER_MAX.message)
        }
    }

    protected fun ensureBetween(min: Instant, max: Instant) {
        if (value.isBefore(min) || value.isAfter(max)) {
            throw TimestampVOException(message = TimestampVOMessages.NOT_IN_RANGE.message)
        }
    }
}
