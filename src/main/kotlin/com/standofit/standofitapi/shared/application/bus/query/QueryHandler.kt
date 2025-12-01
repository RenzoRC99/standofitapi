package com.standofit.standofitapi.shared.application.bus.query

interface QueryHandler<Q : Query<R>, R> {
    fun handle(query: Q): R
}
