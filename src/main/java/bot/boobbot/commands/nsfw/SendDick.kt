package bot.boobbot.commands.nsfw

import bot.boobbot.flight.CommandProperties
import bot.boobbot.models.SendCommand

@CommandProperties(description = "Sends dicks to you or another user", guildOnly = true, aliases = ["sd"])
class SendDick : SendCommand("dicks", "penis")
