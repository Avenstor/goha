package com.discord.bot.weather;

import com.discord.bot.GenericMessageListener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class WeatherListener extends GenericMessageListener {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (message.startsWith("!weather") || message.startsWith("!pogoda")) {
            String weatherMessage = getWeather(event);
            sendMessage(event, weatherMessage);
        }
    }

    private String getWeather(MessageReceivedEvent event) {
        String temp = getDiscordMessage(event);
        String[] words = temp.split(" ");
        NewWeatherStation newWeatherStation = new NewWeatherStation();
        if (words.length < 2) {
            return "Please provide city to check weather for.";
        } else if(words.length == 2) {
            return newWeatherStation.checkWeather(words[1]);
        } else {
            String cityLink = words[1];
            StringBuilder sB = new StringBuilder(cityLink);
            for (int i = 2; i < words.length; i++){
                sB.append("%20");
                sB.append(words[i]);
            }
            return newWeatherStation.checkWeather(sB.toString());
        }
    }
}
