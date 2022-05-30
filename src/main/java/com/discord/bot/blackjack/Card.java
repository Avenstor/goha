package com.discord.bot.blackjack;

import lombok.Getter;

public class Card {

    private final @Getter Type name;
    private final @Getter Color color;
    private final @Getter int value;
    private boolean available;

    public Card(Type type, Color color, int value){
        this.name = type;
        this.color = color;
        this.value = value;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
