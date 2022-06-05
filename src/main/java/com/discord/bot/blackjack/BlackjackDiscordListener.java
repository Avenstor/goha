package com.discord.bot.blackjack;

import com.discord.bot.GenericMessageListener;
import com.discord.bot.blackjack.languagepack.MessageService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class BlackjackDiscordListener extends GenericMessageListener {

    private final Blackjack blackjack;

    public BlackjackDiscordListener(MessageService msgService) {
        this.blackjack = new Blackjack(msgService);
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (event.getAuthor().isBot()) {
            return;
        }
        String outputDiscordMessage = blackjack.userInput(getAuthorId(event), message);
        if (!outputDiscordMessage.isEmpty()) {
            sendMessage(event, outputDiscordMessage);
        }
    }

}
