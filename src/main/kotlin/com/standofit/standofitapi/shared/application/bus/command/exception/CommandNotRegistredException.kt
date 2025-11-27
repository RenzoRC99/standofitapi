package com.standofit.standofitapi.shared.application.bus.command.exception

import com.standofit.standofitapi.shared.error.BaseError

class CommandNotRegisteredException(commandClass: Class<*>) : BaseError(
    code = CommandErrorMessages.COMMAND_NOT_REGISTERED.code,
    message = "No CommandHandler registered for ${commandClass.simpleName}"
)
