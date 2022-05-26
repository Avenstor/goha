package weather;

import alexa.Alexa;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class WeatherListener extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (message.startsWith("!weather")) {
            String weatherMessage = getWeather(event, Unit.IMPERIAL);
            sendMessage(event, weatherMessage);
        }
        //pogoda zawiercie
        if (message.startsWith("!pogoda")) {
            String weatherMessage = getWeather(event, Unit.METRIC);
            sendMessage(event, weatherMessage);
        }
    }

    private String getWeather(MessageReceivedEvent event, Unit unit) {
        String asd = event.getMessage().getContentRaw();
        String[] words = asd.split(" ");
        if (words.length < 2) {
            return "oj nie nie byczq -1";
        } else {
            WeatherStation weatherStation = new WeatherStation();
            return weatherStation.checkWeather(words[1], unit);
        }
    }

    private String getDiscordMessage(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw();
    }

    private void sendMessage(MessageReceivedEvent event, String message){
        event.getChannel().sendMessage(message).queue();
    }
}
