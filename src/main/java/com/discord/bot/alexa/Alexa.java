package com.discord.bot.alexa;

import com.discord.bot.GenericMessageListener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Alexa extends GenericMessageListener {

    private final List<String> tip = new ArrayList<>();

    public Alexa() {
        this.tip.add("Try Out Different Characters");
        this.tip.add("Learn the Map");
        this.tip.add("Try to Learn One Position Really Well");
        this.tip.add("Learn the Hotkeys");
        this.tip.add("Stay Behind Minions");
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = getDiscordMessage(event);
        if (message.equalsIgnoreCase("!tip")) {
            sendMessage(event, rollRandomTip());
        }
    }

    public String rollRandomTip() {
        Random rng = new Random();
        int number = rng.nextInt(5);
        return tip.get(number);
    }

}
