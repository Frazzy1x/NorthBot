package bot.boobbot.commands.nsfw

import bot.boobbot.flight.api.Category
import bot.boobbot.flight.annotations.CommandProperties
import bot.boobbot.models.BbApiCommand

@CommandProperties(description = "Everything is better with toys \uD83D\uDE09", nsfw = true, category = Category.KINKS)
class Toys : BbApiCommand("toys")
