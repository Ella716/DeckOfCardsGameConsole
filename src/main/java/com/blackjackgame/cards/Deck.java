package com.blackjackgame.cards;

import com.blackjackgame.httpclient.CustomHttpClient;
import com.blackjackgame.httpclient.responses.CardResponse;
import com.blackjackgame.httpclient.responses.ShuffleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.LinkedList;


public class Deck {
    private LinkedList<Card> cards;
    private String deckId;

    public Deck() {
        createNewDeck();
    }

     private void createNewDeck() {
         System.out.println("Getting and shuffling new deck...");
         String resBody = CustomHttpClient.customGet("/new/shuffle/?deck_count=1");
         GsonBuilder builder = new GsonBuilder();
         Gson gson = builder.create();
         ShuffleResponse shuffleResponse = gson.fromJson(resBody, ShuffleResponse.class);
         deckId = shuffleResponse.getDeck_id();
         System.out.println("Drawing cards...");
         initializeDeck();
     }


    private void initializeDeck() {
        String resBody = CustomHttpClient.customGet("/" + deckId + "/draw/?count=52");
//        System.out.println(resBody);
        Gson gson = new Gson();
        CardResponse cardsData = gson.fromJson(resBody,CardResponse.class);
        cards = new LinkedList<>(cardsData.getCards());
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            initializeDeck();
        }

        return cards.removeFirst();
    }


//    public void shuffle() {
//        cards.clear();
//        createNewDeck();
//    }
//
//    public String getDeckId() {
//        return deckId;
//    }
//
//    public boolean isEmpty() {
//        return cards.isEmpty();
//    }
}
