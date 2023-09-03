package com.blackjackgame.httpclient.responses;

import com.blackjackgame.cards.Card;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardResponse {
    @SerializedName("cards")
    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }
}
