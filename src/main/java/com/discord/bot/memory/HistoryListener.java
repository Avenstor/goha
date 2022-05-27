package com.discord.bot.memory;

import com.discord.bot.GenericMessageListener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HistoryListener extends GenericMessageListener {

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        List<String> memory = new ArrayList<>();
        if (message.equalsIgnoreCase("!memory")) {
            sendMessage(event, "3 zł");
        }
    }
}
