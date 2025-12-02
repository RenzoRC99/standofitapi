package shared.domain.voexceptions.uuid

import shared.domain.voexceptions.ValueObjectException

class UUIDVOException(
    code: String? = null,
    message: String,
    cause: Throwable? = null
) : ValueObjectException(code, message, cause)
