package com.discord.bot.weather;

import com.discord.bot.GenericMessageListener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class WeatherListener extends GenericMessageListener {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (message.startsWith("!com.discord.bot.weather")) {
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
        String asd = getDiscordMessage(event);
        String[] words = asd.split(" ");
        if (words.length < 2) {
            return "oj nie nie byczq -1";
        } else {
            WeatherStation weatherStation = new WeatherStation();
            return weatherStation.checkWeather(words[1], unit);
        }
    }
}
