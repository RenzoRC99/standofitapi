package com.standofit.standofitapi.shared.application.bus.command

interface CommandBus {
    fun <C : Command> dispatch(command: C)
}
