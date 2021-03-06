package bot.boobbot.entities.internals

import bot.boobbot.BoobBot
import bot.boobbot.entities.framework.ExecutableCommand
import bot.boobbot.entities.framework.Indexer

class CommandRegistry(
    private val commands: Map<String, ExecutableCommand>
) : Map<String, ExecutableCommand> by commands {

    init {
        BoobBot.log.info("Successfully loaded ${commands.size} commands!")
    }

    fun findCommand(commandName: String): ExecutableCommand? {
        return commands[commandName]
            ?: values.firstOrNull { it.properties.aliases.contains(commandName) }
    }

    companion object {
        fun load(): CommandRegistry {
            val indexer = Indexer("bot.boobbot.commands")
            val commands = indexer.getCommands().associateBy { it.name }
            return CommandRegistry(commands)
        }
    }

}