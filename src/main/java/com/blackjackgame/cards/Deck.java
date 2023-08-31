package com.blackjackgame.cards;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private LinkedList<Card> cards;
    private String deckId;
    private static final String API_URL = "https://deckofcardsapi.com/api/deck";

    public Deck() {
        createNewDeck();
    }

    private void createNewDeck() {
        try {
            URL url = new URL(API_URL + "/new/shuffle/?deck_count=1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                Reader reader = new InputStreamReader(conn.getInputStream());
                Gson gson = new Gson();
                String json = reader.toString();
                deckId = gson.fromJson(json, String.class);
                initializeDeck();
            } else {
                System.out.println("Failed to create a new deck. Status code: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initializeDeck() {
        try {
            URL url = new URL(API_URL + "/" + deckId + "/draw/?count=52");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                Reader reader = new InputStreamReader(conn.getInputStream());
                Gson gson = new Gson();
                List<Card> cardsData = gson.fromJson(reader, new TypeToken<List<Card>>() {}.getType());
                cards = new LinkedList<>(cardsData);
            } else {
                System.out.println("Failed to initialize the deck. Status code: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            initializeDeck();
        }

        return cards.removeFirst();
    }


    public void shuffle() {
        cards.clear();
        initializeDeck();
    }

    public String getDeckId() {
        return deckId;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
