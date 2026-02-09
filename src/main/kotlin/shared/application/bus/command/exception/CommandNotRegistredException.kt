package shared.application.bus.command.exception

import shared.error.BaseError

class CommandNotRegisteredException(commandClass: Class<*>) : BaseError(
    code = CommandErrorMessages.COMMAND_NOT_REGISTERED.code,
    message = "No CommandHandler registered for ${commandClass.simpleName}"
)
