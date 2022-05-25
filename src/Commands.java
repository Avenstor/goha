import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Commands extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (message.equalsIgnoreCase("!goha")) {
           sendMessage(event, "3 zł");
        }
        if (message.equalsIgnoreCase("!tip")) {
            Tips tips = new Tips();
            sendMessage(event, tips.rollRandomTip());
        }
        if (message.startsWith("!weather")) {
            String weatherMessage = getWeather(event, "imperial");
            sendMessage(event, weatherMessage);
        }
        //pogoda zawiercie
        if (message.startsWith("!pogoda")) {
            String weatherMessage = getWeather(event, "metric");
            sendMessage(event, weatherMessage);
        }
    }

    private String getWeather(MessageReceivedEvent event, String unit) {
        String asd = event.getMessage().getContentRaw();
        String[] words = asd.split(" ");
        if (words.length < 2) {
            return "oj nie nie byczq -1";
        } else {
            Weather weather = new Weather();
            return weather.checkWeather(words[1], unit);
        }
    }

    private String getDiscordMessage(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw();
    }

    private void sendMessage(MessageReceivedEvent event, String message){
        event.getChannel().sendMessage(message).queue();
    }
}
