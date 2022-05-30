package com.discord.bot.blackjack;

public class Card {

    private final Type name;
    private final Color color;
    private final int value;
    private boolean available;

    public Card(Type type, Color color, int value){
        this.name = type;
        this.color = color;
        this.value = value;
        this.available = true;
    }

    public Type getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
