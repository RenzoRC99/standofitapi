package shared.application.bus.command

interface CommandHandler<C : shared.application.bus.command.Command> {
    fun handle(command: C)
}
