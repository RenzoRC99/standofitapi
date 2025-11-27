package com.standofit.standofitapi.shared.application.bus.command.exception

import com.standofit.standofitapi.shared.error.ErrorInfo

enum class CommandErrorMessages(
    override val code: String,
    override val message: String
) : ErrorInfo {
    COMMAND_NOT_REGISTERED("COMMAND_NOT_REGISTERED", "No CommandHandler registered for this command")
}
