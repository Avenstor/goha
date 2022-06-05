package com.discord.bot.blackjack;

import lombok.Getter;

import java.util.Random;

@Getter
public class BlackjackLogic {
    private final Deck deck = new Deck();
    private final Hand playerHand = new Hand();
    private final Hand computerHand = new Hand();

    private int playerScore = 0;

    private int computerScore = 0;

    public void startGame() {
        deck.createDeck52();
        dealCards(deck, playerHand, computerHand);
    }

    public void finishTurn(){
        playerScore = calculateScore(getPlayerHand());
        computerBehavior(deck, computerHand);
        computerScore = calculateScore(getComputerHand());
    }

    public boolean isPlayerScoreOver21(){
        return calculateScore(playerHand) > 21;
    }

    public void dealCards(Deck deck, Hand player, Hand computer) {
        drawCard(deck, player);
        drawCard(deck, computer);
        drawCard(deck, player);
        drawCard(deck, computer);
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

    private void computerBehavior(Deck deck, Hand computer) {
        int computerScore = calculateScore(computer);
        while (computerScore <= 16) {
            drawCard(deck, computer);
            computerScore = calculateScore(computer);
        }
    }



    public int result() {
        int playerScore = getPlayerScore();
        int computerScore = getComputerScore();
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
