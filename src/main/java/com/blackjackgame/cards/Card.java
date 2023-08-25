package com.blackjackgame.cards;

public class Card {
    private String value;
    private String suit;
    private String code;
    private boolean faceUp;

    public Card(String value, String suit, String code) {
        this.value = value;
        this.suit = suit;
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

}
