package shared.application.bus.query.exception

import shared.error.ErrorInfo

enum class QueryErrorMessages(
    override val code: String,
    override val message: String
) : ErrorInfo {
    QUERY_NOT_REGISTERED("QUERY_NOT_REGISTERED", "No QueryHandler registered for this query")
}
