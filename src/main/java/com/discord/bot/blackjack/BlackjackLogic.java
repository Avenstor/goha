package com.discord.bot.blackjack;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class BlackjackLogic {
    private final Deck deck = new Deck();
    @Getter
    private final List<Hand> hands = new ArrayList<>();
    @Getter
    private final long authorDiscordId;

    public BlackjackLogic(long authorId) {
        hands.add(new Hand(authorId));
        hands.add(new Hand());
        authorDiscordId = authorId;
    }

    public BlackjackLogic(long authorId, long secondPlayerId) {
        hands.add(new Hand(authorId));
        hands.add(new Hand(secondPlayerId));
        authorDiscordId = authorId;
    }

    public void startGame() {
        deck.createDeck52();
        dealCards(deck, hands.get(0), hands.get(1));
    }

    public void finishTurn(long authorId){
        hands.get(getIndexByAuthorId(authorId)).calculateScore();
        if(hands.get(1).getHandId() == 0){
            computerBehavior(deck, hands.get(1));
            hands.get(1).calculateScore();
        }
    }


    //TODO: Find suitable list method instead of reinventing wheel xD
    public int getIndexByAuthorId(long authorId){
        for(int i = 0; i < hands.size(); i++){
            if(hands.get(i) != null && hands.get(i).getHandId() == authorId){
                return i;
            }
        }
        return 0;
    }

    public boolean isPlayerScoreOver21(Hand hand){
        return hand.calculateScore() > 21;
    }

    public void dealCards(Deck deck, Hand playerOne, Hand playerTwo) {
        drawCard(deck, playerOne);
        drawCard(deck, playerTwo);
        drawCard(deck, playerOne);
        drawCard(deck, playerTwo);
    }

    public void drawCard(Deck deck, Hand hand) {
        Random rng = new Random();
        int newRandomCard;
        do {
            newRandomCard = rng.nextInt(deck.getDeck().size());
            if (deck.getDeck().get(newRandomCard).isAvailable()) {
                hand.getHand().add(deck.getDeck().get(newRandomCard));
            }
        } while (!deck.getDeck().get(newRandomCard).isAvailable());
        deck.getDeck().get(newRandomCard).setAvailable(false);
    }

    public int calculateScore(Hand hand) {
        int score = 0;
        int handSize = hand.getHand().size();
        for (int i = 0; i < handSize; i++) {
            score += hand.getHand().get(i).getValue();
        }
        return score;
    }

    private void computerBehavior(Deck deck, Hand player) {
        int score = player.calculateScore();
        while (score <= 16) {
            drawCard(deck, player);
            score = player.calculateScore();
        }
    }



    public int singleplayerResult() {
        int playerScore = hands.get(0).getScore();
        int computerScore = hands.get(1).getScore();
        if (playerScore == 21 && computerScore == 21) {
            return 0;
        } else if (playerScore < 21 && computerScore > 21) {
            return 1;
        } else if (playerScore > computerScore) {
            return 1;
        } else if (playerScore == computerScore) {
            return 0;
        } else {
            return -1;
        }
    }

}
