package com.discord.bot.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final @Getter List<Card> deck = new ArrayList<>();

    public void createDeck52(){
        Type[] values = Type.values();
        Color[] colors = Color.values();
        for (Type value : values) {
            for (Color color : colors) {
                this.deck.add(new Card(value, color, value.getValue()));
            }
        }
    }
}
