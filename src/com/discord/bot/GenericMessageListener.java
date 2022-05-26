package com.discord.bot;

public abstract class GenericMessageListener {

    abstract void onMessageReceived(MessageReceivedEvent event);

    private String getDiscordMessage(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw();
    }

    private void sendMessage(MessageReceivedEvent event, String message){
        event.getChannel().sendMessage(message).queue();
    }

}
