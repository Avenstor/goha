package com.discord.bot.blackjack;

import com.discord.bot.GenericMessageListener;
import com.discord.bot.blackjack.languagepack.MessageService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class BlackjackDiscordListener extends GenericMessageListener {

    private final Blackjack blackjack;

    public BlackjackDiscordListener(MessageService msgService, JDA jda) {
        this.blackjack = new Blackjack(msgService, jda);
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (event.getAuthor().isBot()) {
            return;
        }
        if (event.isFromType(ChannelType.PRIVATE)) {
            handlePrivateMessage(event);
        } else {
            handleGuildMessage(event);
        }

    }

}
