package com.blackjackgame.cards;

import com.blackjackgame.httpclient.CustomHttpClient;
import com.blackjackgame.httpclient.responses.CardResponse;
import com.blackjackgame.httpclient.responses.ShuffleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Deck {
    private LinkedList<Card> cards;
    private String deckId;

    public Deck() {
        createNewDeck();
    }

     private void createNewDeck() {
         String resBody = CustomHttpClient.customGet("/new/shuffle/?deck_count=1");
         GsonBuilder builder = new GsonBuilder();
         Gson gson = builder.create();
         ShuffleResponse shuffleResponse = gson.fromJson(resBody, ShuffleResponse.class);
         deckId = shuffleResponse.getDeck_id();
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


    public void shuffle() {
        cards.clear();
        createNewDeck();
    }

    public String getDeckId() {
        return deckId;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
