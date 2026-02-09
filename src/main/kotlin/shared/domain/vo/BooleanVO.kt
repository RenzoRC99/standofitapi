package shared.domain.vo

import shared.domain.voexceptions.bool.BooleanVOException
import shared.domain.voexceptions.bool.BooleanVOMessages


abstract class BooleanVO(
    value: Boolean
) : BaseVO<Boolean>(value) {

    protected fun ensureTrue() {
        if (!value) {
            throw BooleanVOException(message = BooleanVOMessages.MUST_BE_TRUE.message)
        }
    }

    protected fun ensureFalse() {
        if (value) {
            throw BooleanVOException(message = BooleanVOMessages.MUST_BE_FALSE.message)
        }
    }
}
