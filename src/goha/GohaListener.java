package goha;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GohaListener extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (message.equalsIgnoreCase("!goha")) {
           sendMessage(event, "3 zł");
        }

    }

    private String getDiscordMessage(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw();
    }

    private void sendMessage(MessageReceivedEvent event, String message){
        event.getChannel().sendMessage(message).queue();
    }
}
