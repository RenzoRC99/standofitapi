package shared.domain.vo

import shared.domain.voexceptions.biginteger.BigIntegerVOException
import shared.domain.voexceptions.biginteger.BigIntegerVOMessages
import java.math.BigInteger

abstract class BigIntegerVO(
    value: BigInteger
) : BaseVO<BigInteger>(value) {

    protected fun ensurePositive() {
        if (value <= BigInteger.ZERO) {
            throw BigIntegerVOException(
                code = BigIntegerVOMessages.NOT_POSITIVE.code,
                message = BigIntegerVOMessages.NOT_POSITIVE.message
            )
        }
    }

    protected fun ensureNegative() {
        if (value >= BigInteger.ZERO) {
            throw BigIntegerVOException(
                code = BigIntegerVOMessages.NOT_NEGATIVE.code,
                message = BigIntegerVOMessages.NOT_NEGATIVE.message
            )
        }
    }

    protected fun ensureMin(min: BigInteger) {
        if (value < min) {
            throw BigIntegerVOException(
                code = BigIntegerVOMessages.TOO_SMALL.code,
                message = BigIntegerVOMessages.TOO_SMALL.message
            )
        }
    }

    protected fun ensureMax(max: BigInteger) {
        if (value > max) {
            throw BigIntegerVOException(
                code = BigIntegerVOMessages.TOO_LARGE.code,
                message = BigIntegerVOMessages.TOO_LARGE.message
            )
        }
    }

    protected fun ensureNotZero() {
        if (value == BigInteger.ZERO) {
            throw BigIntegerVOException(
                code = BigIntegerVOMessages.ZERO_NOT_ALLOWED.code,
                message = BigIntegerVOMessages.ZERO_NOT_ALLOWED.message
            )
        }
    }
}
