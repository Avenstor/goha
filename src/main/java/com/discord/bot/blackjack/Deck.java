package com.discord.bot.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> deck = new ArrayList<>();

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public void createDeck52(){
        this.deck.add(new Card(Type.ACE, Color.HEARTS, 11));
        this.deck.add(new Card(Type.ACE, Color.DIAMONDS, 11));
        this.deck.add(new Card(Type.ACE, Color.SPADES, 11));
        this.deck.add(new Card(Type.ACE, Color.CLUBS, 11));
        this.deck.add(new Card(Type.KING, Color.HEARTS, 4));
        this.deck.add(new Card(Type.KING, Color.DIAMONDS, 4));
        this.deck.add(new Card(Type.KING, Color.SPADES, 4));
        this.deck.add(new Card(Type.KING, Color.CLUBS, 4));
        this.deck.add(new Card(Type.QUEEN, Color.HEARTS, 3));
        this.deck.add(new Card(Type.QUEEN, Color.DIAMONDS, 3));
        this.deck.add(new Card(Type.QUEEN, Color.SPADES, 3));
        this.deck.add(new Card(Type.QUEEN, Color.CLUBS, 3));
        this.deck.add(new Card(Type.JACK, Color.HEARTS, 2));
        this.deck.add(new Card(Type.JACK, Color.DIAMONDS, 2));
        this.deck.add(new Card(Type.JACK, Color.SPADES, 2));
        this.deck.add(new Card(Type.JACK, Color.CLUBS, 2));
        this.deck.add(new Card(Type.TEN, Color.HEARTS, 10));
        this.deck.add(new Card(Type.TEN, Color.DIAMONDS, 10));
        this.deck.add(new Card(Type.TEN, Color.SPADES, 10));
        this.deck.add(new Card(Type.TEN, Color.CLUBS, 10));
        this.deck.add(new Card(Type.NINE, Color.HEARTS, 9));
        this.deck.add(new Card(Type.NINE, Color.DIAMONDS, 9));
        this.deck.add(new Card(Type.NINE, Color.SPADES, 9));
        this.deck.add(new Card(Type.NINE, Color.CLUBS, 9));
        this.deck.add(new Card(Type.EIGHT, Color.HEARTS, 8));
        this.deck.add(new Card(Type.EIGHT, Color.DIAMONDS, 8));
        this.deck.add(new Card(Type.EIGHT, Color.SPADES, 8));
        this.deck.add(new Card(Type.EIGHT, Color.CLUBS, 8));
        this.deck.add(new Card(Type.SEVEN, Color.HEARTS, 7));
        this.deck.add(new Card(Type.SEVEN, Color.DIAMONDS, 7));
        this.deck.add(new Card(Type.SEVEN, Color.SPADES, 7));
        this.deck.add(new Card(Type.SEVEN, Color.CLUBS, 7));
        this.deck.add(new Card(Type.SIX, Color.HEARTS, 6));
        this.deck.add(new Card(Type.SIX, Color.DIAMONDS, 6));
        this.deck.add(new Card(Type.SIX, Color.SPADES, 6));
        this.deck.add(new Card(Type.SIX, Color.CLUBS, 6));
        this.deck.add(new Card(Type.FIVE, Color.HEARTS, 5));
        this.deck.add(new Card(Type.FIVE, Color.DIAMONDS, 5));
        this.deck.add(new Card(Type.FIVE, Color.SPADES, 5));
        this.deck.add(new Card(Type.FIVE, Color.CLUBS, 5));
        this.deck.add(new Card(Type.FOUR, Color.HEARTS, 4));
        this.deck.add(new Card(Type.FOUR, Color.DIAMONDS, 4));
        this.deck.add(new Card(Type.FOUR, Color.SPADES, 4));
        this.deck.add(new Card(Type.FOUR, Color.CLUBS, 4));
        this.deck.add(new Card(Type.THREE, Color.HEARTS, 3));
        this.deck.add(new Card(Type.THREE, Color.DIAMONDS, 3));
        this.deck.add(new Card(Type.THREE, Color.SPADES, 3));
        this.deck.add(new Card(Type.THREE, Color.CLUBS, 3));
        this.deck.add(new Card(Type.TWO, Color.HEARTS, 2));
        this.deck.add(new Card(Type.TWO, Color.DIAMONDS, 2));
        this.deck.add(new Card(Type.TWO, Color.SPADES, 2));
        this.deck.add(new Card(Type.TWO, Color.CLUBS, 2));
    }

}
