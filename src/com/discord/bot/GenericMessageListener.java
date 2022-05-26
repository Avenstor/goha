package com.discord.bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class GenericMessageListener extends ListenerAdapter {


    protected String getDiscordMessage(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw();
    }

    protected void sendMessage(MessageReceivedEvent event, String message){
        event.getChannel().sendMessage(message).queue();
    }

}
