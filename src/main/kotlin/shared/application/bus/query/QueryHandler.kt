package shared.application.bus.query

interface QueryHandler<Q : Query<R>, R> {
    fun handle(query: Q): R
}
