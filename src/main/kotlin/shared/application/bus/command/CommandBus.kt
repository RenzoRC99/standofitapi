package shared.application.bus.command

interface CommandBus {
    fun <C : shared.application.bus.command.Command> dispatch(command: C)
}
