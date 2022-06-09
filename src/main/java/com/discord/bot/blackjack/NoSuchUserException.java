package com.discord.bot.blackjack;

public class NoSuchUserException extends Exception{

    String message;

    public NoSuchUserException(String message) {
        this.message = message;
    }

    public String toString (){
        return "No User found: " + message;
    }
}
