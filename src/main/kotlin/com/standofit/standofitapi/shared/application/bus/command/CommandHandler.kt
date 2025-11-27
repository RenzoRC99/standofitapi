package com.standofit.standofitapi.shared.application.bus.command

interface CommandHandler<C : Command> {
    fun handle(command: C)
}
