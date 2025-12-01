package com.standofit.standofitapi.shared.application.bus.query.exception

import com.standofit.standofitapi.shared.error.BaseError

class QueryNotRegisteredException(queryClass: Class<*>) : BaseError(
    code = QueryErrorMessages.QUERY_NOT_REGISTERED.code,
    message = "No QueryHandler registered for ${queryClass.simpleName}"
)
