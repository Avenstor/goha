package com.discord.bot.blackjack;

import java.util.Random;

public class Game {
    private final Deck deck = new Deck();
    private final Hand playerHand = new Hand();
    private final Hand computerHand = new Hand();
    private int playerScore = 0;
    private int computerScore = 0;

    public void startGame() {
        deck.createDeck52();
        dealCards(deck, playerHand, computerHand);
    }

    public void dealCards(Deck deck, Hand player, Hand computer){
        Random rng = new Random();
        //1st card for player
        int randomCard = rng.nextInt(deck.getDeck().size());
        player.getHand().add(deck.getDeck().get(randomCard));
        deck.getDeck().get(randomCard).setAvailable(false);
        //2nd card for computer
        do {
            randomCard = rng.nextInt(deck.getDeck().size());
            if (deck.getDeck().get(randomCard).isAvailable()) {
                computer.getHand().add(deck.getDeck().get(randomCard));
            }
        } while (!deck.getDeck().get(randomCard).isAvailable());
        deck.getDeck().get(randomCard).setAvailable(false);
        //3rd card for player
        while (player.getHand().size() < 2) {
            randomCard = rng.nextInt(deck.getDeck().size());
            if(deck.getDeck().get(randomCard).isAvailable()){
                player.getHand().add(deck.getDeck().get(randomCard));
                deck.getDeck().get(randomCard).setAvailable(false);
            }
        }
        //4th card for computer
        while (computer.getHand().size() < 2) {
            randomCard = rng.nextInt(deck.getDeck().size());
            if(deck.getDeck().get(randomCard).isAvailable()){
                computer.getHand().add(deck.getDeck().get(randomCard));
                deck.getDeck().get(randomCard).setAvailable(false);
            }
        }
    }

    public void drawCard(Deck deck, Hand hand){
        Random rng = new Random();
        int newRandomCard;
        do{
            newRandomCard = rng.nextInt(deck.getDeck().size());
            if (deck.getDeck().get(newRandomCard).isAvailable()) {
                hand.getHand().add(deck.getDeck().get(newRandomCard));
            }
        } while (!deck.getDeck().get(newRandomCard).isAvailable());
        deck.getDeck().get(newRandomCard).setAvailable(false);
    }

    public int calculateScore(Hand hand){
        int score = 0;
        int handSize = hand.getHand().size();
        for(int i=0; i<handSize; i++){
            score += hand.getHand().get(i).getValue();
        }
        return score;
    }

    public void computerBehavior(Deck deck, Hand computer){
        int computerScore = calculateScore(computer);
        while (computerScore <= 16){
            drawCard(deck, computer);
            computerScore = calculateScore(computer);
        }
    }

    public String result(int playerScore, int computerScore){
        if (playerScore == 21 && computerScore == 21){
            return "DRAW";
        } else if (playerScore < 21 && computerScore > 21) {
            return "YOU WIN";
        } else if (playerScore > computerScore){
            return "YOU WIN";
        } else if (playerScore == computerScore){
            return "DRAW";
        } else {
            return "YOU LOSE";
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getComputerHand() {
        return computerHand;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }
}
