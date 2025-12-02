package shared.application.bus.query.exception

import shared.error.BaseError

class QueryNotRegisteredException(queryClass: Class<*>) : BaseError(
    code = QueryErrorMessages.QUERY_NOT_REGISTERED.code,
    message = "No QueryHandler registered for ${queryClass.simpleName}"
)
