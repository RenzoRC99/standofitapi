package shared.application.bus.query

interface QueryBus {
    fun <Q : Query<R>, R> dispatch(query: Q): R
}
