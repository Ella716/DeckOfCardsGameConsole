package com.blackjackgame.httpclient.responses;

public class ShuffleResponse {
    private String deck_id;
    private boolean success;
    private int remaining;
    private boolean shuffled;

    public String getDeck_id() {
        return deck_id;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getRemaining() {
        return remaining;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public ShuffleResponse(){}
}
