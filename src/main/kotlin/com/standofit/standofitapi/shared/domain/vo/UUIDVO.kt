package com.standofit.standofitapi.shared.domain.vo

import com.standofit.standofitapi.shared.domain.voexceptions.uuid.UUIDVOException
import com.standofit.standofitapi.shared.domain.voexceptions.uuid.UUIDVOMessages
import java.util.UUID

abstract class UUIDVO(
    value: UUID
) : BaseVO<UUID>(value) {

    protected fun ensureVersion(version: Int) {
        if (value.version() != version) {
            throw UUIDVOException(message = UUIDVOMessages.INVALID_VERSION.message)
        }
    }

    protected fun ensureVariant(variant: Int) {
        if (value.variant() != variant) {
            throw UUIDVOException(message = UUIDVOMessages.INVALID_VARIANT.message)
        }
    }
}
