package com.discord.bot.blackjack;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    @Getter
    private final List<Card> hand = new ArrayList<>();
    @Getter
    private final long handId;
    @Getter @Setter
    private byte score;

    public Hand(){
        handId = 0;
        score = 0;
    }

    public Hand(long handId) {
        this.handId = handId;
        score = 0;
    }

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

    public int calculateScore(){
        score = 0;
        for (Card card : hand) {
            score += card.getValue();
        }
        return score;
    }

}
