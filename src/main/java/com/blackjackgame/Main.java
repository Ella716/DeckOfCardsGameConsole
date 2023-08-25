package com.blackjackgame;

import com.blackjackgame.logic.BlackjackGameLogic;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Console Blackjack!");

        // Create a new Blackjack game logic
        BlackjackGameLogic gameLogic = new BlackjackGameLogic();

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
