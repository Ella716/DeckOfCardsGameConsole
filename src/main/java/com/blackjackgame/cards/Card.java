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

    public Card(String suit, int rank) {
    }

    public Card(String suit, String hearts) {

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

    public boolean isFaceCard() {
        return value.equals("J") || value.equals("Q") || value.equals("K");
    }

    public int getRank() {
        if (value != null) {
            if (value.equals("Ace")) {
                return 1;
            } else if (value.matches("[2-9]")) {
                return Integer.parseInt(value);
            } else {
                return 10;
            }
        } else {
            return 0;
        }
    }

    public boolean isFaceUp() {
        return faceUp;
    }

}
