package shared.domain.vo

import shared.domain.voexceptions.date.DateVOException
import shared.domain.voexceptions.date.DateVOMessages
import java.time.Instant

abstract class DateVO(
    value: Instant
) : BaseVO<Instant>(value) {

    protected fun ensureNotInPast() {
        if (value.isBefore(Instant.now())) {
            throw DateVOException(message = DateVOMessages.IN_PAST.message)
        }
    }

    protected fun ensureNotInFuture() {
        if (value.isAfter(Instant.now())) {
            throw DateVOException(message = DateVOMessages.IN_FUTURE.message)
        }
    }

    protected fun ensureAfter(min: Instant) {
        if (value.isBefore(min)) {
            throw DateVOException(message = DateVOMessages.BEFORE_MIN.message)
        }
    }

    protected fun ensureBefore(max: Instant) {
        if (value.isAfter(max)) {
            throw DateVOException(message = DateVOMessages.AFTER_MAX.message)
        }
    }

    protected fun ensureBetween(min: Instant, max: Instant) {
        if (value.isBefore(min) || value.isAfter(max)) {
            throw DateVOException(message = DateVOMessages.NOT_IN_RANGE.message)
        }
    }
}
