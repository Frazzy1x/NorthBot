package bot.boobbot.commands.economy

import bot.boobbot.BoobBot
import bot.boobbot.entities.framework.Category
import bot.boobbot.entities.framework.Command
import bot.boobbot.entities.framework.CommandProperties
import bot.boobbot.entities.framework.Context
import bot.boobbot.utils.Formats
import bot.boobbot.utils.Formats.getRemainingCoolDown
import java.awt.Color
import java.time.Instant
import java.time.temporal.ChronoUnit


@CommandProperties(aliases = ["+"], description = "Give rep.", category = Category.ECONOMY)
class Rep : Command {

    override fun execute(ctx: Context) {
        val target = ctx.mentions.firstOrNull()
            ?: return ctx.embed {
                setColor(Color.red)
                setDescription(Formats.error("you didn't mention a @user, dumbass.\n"))
            }

        if (target.idLong == BoobBot.selfId) {
            return ctx.send("Don't you fucking touch me whore, I will end you.")
        }

        if (target.idLong == ctx.author.idLong) {
            return ctx.send("aww how sad you wanna rep yourself, well fucking don't. Go find a friend whore.")
        }

        val author = BoobBot.database.getUser(ctx.author.id)
        val now = Instant.now()

        if (author.lastRep != null) {
            val t = author.lastRep!!.plus(12, ChronoUnit.HOURS)
            val x = t.toEpochMilli() - now.toEpochMilli()
            if (!t.isBefore(now)) {
                return ctx.send("You already gave rep today whore.\nFuck off and try again in ${getRemainingCoolDown(x)}")
            }
        }

        author.lastRep = now
        author.save()

        BoobBot.database.getUser(target.id)
            .apply { rep += 1 }
            .save()

        ctx.send("You gave ${target.asTag} a rep point, Good job! Seems you're not completely useless after all.")
    }

}
