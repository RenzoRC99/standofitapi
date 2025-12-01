package com.standofit.standofitapi.shared.application.bus.query

interface QueryBus {
    fun <Q : Query<R>, R> dispatch(query: Q): R
}
