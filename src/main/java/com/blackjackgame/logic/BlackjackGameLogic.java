package com.blackjackgame.logic;

import com.blackjackgame.cards.Card;
import com.blackjackgame.cards.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class BlackjackGameLogic {
    private Deck deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private boolean playerTurn;

    public BlackjackGameLogic() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        playerTurn = true;

        // Initial deal: Player gets two cards, and the dealer gets one card face up and one card face down.
        playerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void playerHit() {
        if (playerTurn) {
            playerHand.add(deck.drawCard());
            if (getPlayerScore() > 21) {
                playerTurn = false;
                dealerTurn();
            }
        }
    }

    public void playerStand() {
        playerTurn = false;
        dealerTurn();
    }

    public int calculateScore(List<Card> hand) {
        int score = 0;
        int numAces = 0;

        for (Card card : hand) {
            if (card.isFaceCard()) {
                score += 10;
            } else if (card.getValue().matches("[0-9]+")) {
                score += Integer.parseInt(card.getValue());
            } else {
                score += 11;
                numAces++;
            }
        }

        // Handle Aces as 1 when needed to avoid busting
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }

    public int getPlayerScore() {
        return calculateScore(playerHand);
    }

    public int getDealerScore() {
        return calculateScore(dealerHand);
    }

    public void startGame() {

        // Welcome message
        System.out.println("Welcome to Blackjack!");

        // Initialize the game state
        deck.shuffle(); // Shuffle the deck before starting the game

        // Clear the player's and dealer's hands
        playerHand.clear();
        dealerHand.clear();

        // Draw two cards for the player
        playerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());

        // Draw two cards for the dealer
        dealerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());

        // Reveal the facedown card of the dealer
        dealerHand.get(0).setFaceUp(true);

        // Show initial hands
        System.out.println("Your hand: " + playerHand);
        System.out.println("Dealer's hand: [**," + dealerHand.get(0) + "]"); // Hide the dealer's facedown card

        // Player's turn
        playerTurn();

        // Dealer's turn
        dealerTurn();

        // Determine the winner and end the game
        endGame();

        // Ask the player if they want to play again
        System.out.print("Do you want to play again? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String playAgain = scanner.nextLine();
        if (playAgain.equalsIgnoreCase("yes")) {
            startGame(); // Recursive call to start a new game if the player wants to play again
        } else {
            System.out.println("Thank you for playing Blackjack!");
        }
    }

    private void playerTurn() {
        Scanner scanner = new Scanner(System.in);

        while (isPlayerTurn()) {
            System.out.print("Do you want to hit or stand? (h/s): ");
            String decision = scanner.nextLine();

            if (decision.equalsIgnoreCase("h")) {
                playerHit();
                System.out.println("Your hand: " + playerHand);
            } else if (decision.equalsIgnoreCase("s")) {
                playerStand();
            } else {
                System.out.println("Invalid input. Please enter 'h' to hit or 's' to stand.");
            }
        }
    }

    public void dealerTurn() {
        // Reveal the facedown card of the dealer
        dealerHand.get(0).setFaceUp(true);

        // Dealer hits until their score is at least 17
        while (getDealerScore() < 17) {
            dealerHand.add(deck.drawCard());
        }
    }

    public void endGame() {
        playerTurn = false;

        int playerScore = getPlayerScore();
        int dealerScore = getDealerScore();

        if (playerScore > 21) {
            System.out.println("Player busts! Dealer wins.");
        } else if (dealerScore > 21) {
            System.out.println("Dealer busts! Player wins.");
        } else if (playerScore == dealerScore) {
            System.out.println("It's a tie!");
        } else if (playerScore > dealerScore) {
            System.out.println("Player wins!");
        } else {
            System.out.println("Dealer wins!");
        }
    }
}