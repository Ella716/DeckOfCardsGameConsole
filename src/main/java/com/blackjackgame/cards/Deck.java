package com.blackjackgame.cards;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

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
                Scanner sc = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();
                while (sc.hasNextLine()) {
                    response.append(sc.nextLine());
                }
                sc.close();

                String deckIdKey = "\"deck_id\":";
                int deckIdIndex = response.indexOf(deckIdKey);
                if (deckIdIndex != -1) {
                    int deckIdStart = deckIdIndex + deckIdKey.length() + 2; // Skip colon and double quotes
                    int deckIdEnd = response.indexOf("\"", deckIdStart);
                    deckId = response.substring(deckIdStart, deckIdEnd);
                    initializeDeck();
                } else {
                    System.out.println("Failed to create a new deck. Response: " + response);
                }
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
                Scanner sc = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();
                while (sc.hasNextLine()) {
                    response.append(sc.nextLine());
                }
                sc.close();

                String[] cardsData = response.toString().split("\"code\":\"");
                cards = new LinkedList<>();
                for (int i = 1; i < cardsData.length; i++) {
                    String cardCode = cardsData[i].split("\"")[0];
                    String suit = cardCode.substring(cardCode.length() - 1);
                    String value = cardCode.substring(0, cardCode.length() - 1);
                    cards.add(new Card(value, suit, cardCode));
                }
            } else {
                System.out.println("Failed to initialize the deck. Status code: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Card drawCard() {
        try {
            URL url = new URL(API_URL + "/" + deckId + "/draw/?count=1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                Scanner sc = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();
                while (sc.hasNextLine()) {
                    response.append(sc.nextLine());
                }
                sc.close();

                String cardCode = response.toString().split("\"code\":\"")[0];
                String suit = cardCode.substring(cardCode.length() - 1);
                String value = cardCode.substring(0, cardCode.length() - 1);
                return new Card(value, suit, cardCode);
            } else {
                System.out.println("Failed to draw a card. Status code: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void shuffle() {
        try {
            URL url = new URL(API_URL + "/" + deckId + "/shuffle/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                System.out.println("Failed to shuffle the deck. Status code: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
