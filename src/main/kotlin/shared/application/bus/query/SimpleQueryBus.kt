package shared.application.bus.query

import shared.application.bus.query.exception.QueryNotRegisteredException

class SimpleQueryBus(
    private val handlers: Map<Class<out Query<*>>, QueryHandler<out Query<*>, *>>
) : QueryBus {

    @Suppress("UNCHECKED_CAST")
    override fun <Q : Query<R>, R> dispatch(query: Q): R {
        val handler = handlers[query::class.java] as? QueryHandler<Q, R>
            ?: throw QueryNotRegisteredException(query::class.java)
        return handler.handle(query)
    }
}
