package com.blackjackgame;

import com.blackjackgame.logic.BlackjackGameLogic;

import java.util.Scanner;

public class Main {

    private BlackjackGameLogic gameLogic;

    public BlackjackGameLogic getGameLogic() {
        return gameLogic;
    }

    public Main() {
        // Initialize gameLogic here
        gameLogic = new BlackjackGameLogic();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Console Blackjack!");

        // Create a new Blackjack game logic
        BlackjackGameLogic gameLogic = new BlackjackGameLogic();

        // Start the game
        gameLogic.startGame();

        Scanner scanner = new Scanner(System.in);
        scanner.next(); // Provide some input to the Scanner object

        while (true) {
            System.out.println("\nPlayer Hand: " + gameLogic.getPlayerHand());
            System.out.println("Dealer Hand: " + gameLogic.getDealerHand());

            if (gameLogic.isPlayerTurn()) {
                System.out.print("Do you want to (H)it or (S)tand? ");
                String choice = scanner.next();

                if ("H".equalsIgnoreCase(choice)) {
                    gameLogic.playerHit();
                } else if ("S".equalsIgnoreCase(choice)) {
                    gameLogic.playerStand();
                } else {
                    System.out.println("Invalid choice. Please enter H or S.");
                }
            } else {
                gameLogic.dealerTurn();
                gameLogic.endGame();
                break;
            }
        }

        scanner.close();
    }
}
