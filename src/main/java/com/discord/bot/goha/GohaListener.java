package com.discord.bot.goha;

import com.discord.bot.GenericMessageListener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class GohaListener extends GenericMessageListener {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (message.equalsIgnoreCase("!goha")) {
           sendMessage(event, "3 zł");
        }

    }

}
