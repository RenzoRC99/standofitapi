package shared.application.bus.command

import shared.application.bus.command.exception.CommandNotRegisteredException

class SimpleCommandBus(
    private val handlers: Map<Class<out Command>, CommandHandler<out Command>>
) : CommandBus {

    @Suppress("UNCHECKED_CAST")
    override fun <C : Command> dispatch(command: C) {
        val handler = handlers[command::class.java] as? CommandHandler<C>
            ?: throw CommandNotRegisteredException(command::class.java)
        handler.handle(command)
    }
}
