package com.discord.bot;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class GenericMessageListener extends ListenerAdapter {

    protected String getDiscordMessage(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw();
    }

    protected void sendMessage(MessageReceivedEvent event, String message){
        event.getChannel().sendMessage(message).queue();
    }

    protected String getAuthorId(MessageReceivedEvent event){
        return event.getAuthor().getId();
    }

    protected void sendDirectMessage(User user, String content){
        user.openPrivateChannel().flatMap(channel -> channel.sendMessage(content)).queue();
    }

}
