package com.discord.bot.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> deck = new ArrayList<>();

    public List<Card> getDeck() {
        return deck;
    }

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
