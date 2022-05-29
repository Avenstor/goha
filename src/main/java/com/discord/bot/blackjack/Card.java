package com.discord.bot.blackjack;

public class Card {

    private Type name;
    private Color color;
    private int value;
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

    public void setName(Type name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
