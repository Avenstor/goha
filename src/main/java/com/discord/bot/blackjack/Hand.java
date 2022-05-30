package com.discord.bot.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final @Getter List<Card> hand = new ArrayList<>();

    public String getHandDescription() {
        String currentHand = "";
        StringBuilder sb = new StringBuilder(currentHand);
        hand.forEach(card -> {
            sb.append(card.getName().toString());
            sb.append(" of ");
            sb.append(card.getColor().toString()).append("\n");
        });
        return sb.toString();
    }

}
