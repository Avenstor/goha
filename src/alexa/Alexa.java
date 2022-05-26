package alexa;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Alexa extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (message.equalsIgnoreCase("!tip")) {
            Alexa alexa = new Alexa();
            sendMessage(event, alexa.rollRandomTip());
        }
    }

    private final String[] tip = new String[]{
            "Try Out Different Characters",
            "Learn the Map",
            "Try to Learn One Position Really Well",
            "Learn the Hotkeys",
            "Stay Behind Minions"
    };

    public String rollRandomTip() {
        Random rng = new Random();
        int number = rng.nextInt(5);
        return tip[number];
    }

    private String getDiscordMessage(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw();
    }

    private void sendMessage(MessageReceivedEvent event, String message){
        event.getChannel().sendMessage(message).queue();
    }

}
