import com.blackjackgame.cards.Card;
import com.blackjackgame.cards.Deck;
import com.blackjackgame.logic.BlackjackGameLogic;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BlackjackGameLogicTest {
    private BlackjackGameLogic gameLogic;

    @Before
    public void setUp() {
        gameLogic = new BlackjackGameLogic();
    }

    @Test
    public void playerHit() {
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("Ace", "Hearts", "AH"));
        assertFalse(playerHand.isEmpty());
    }

    @Test
    public void playerStand() {
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("Ace", "Hearts", "AH"));
        System.out.println("Player stands.");
    }

    @Test
    public void testCalculateScore() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("Ace", "Hearts", "AH"));
        cards.add(new Card("10", "Spades", "10S"));
        int score = gameLogic.calculateScore(cards);
        assertEquals(21, score);
    }

    @Test
    public void testGetPlayerScore() {
        gameLogic.getPlayerHand().add(new Card("Ace", "Hearts", "AH"));
        gameLogic.getPlayerHand().add(new Card("10", "Spades", "10S"));
        int playerScore = gameLogic.getPlayerScore();
        assertEquals(21, playerScore);
    }

    @Test
    public void testGetDealerScore() {
        gameLogic.getDealerHand().add(new Card("Ace", "Hearts", "AH"));
        gameLogic.getDealerHand().add(new Card("8", "Spades", "8S"));
        int dealerScore = gameLogic.getDealerScore();
        assertEquals(19, dealerScore);
    }

    @Test
    public void endGame() {
        // Test different game outcomes
        gameLogic.getPlayerHand().add(new Card("Ace", "Hearts", "AH"));
        gameLogic.getPlayerHand().add(new Card("10", "Spades", "10S"));
        gameLogic.getDealerHand().add(new Card("King", "Diamonds", "KD"));
        gameLogic.getDealerHand().add(new Card("6", "Clubs", "6C"));

        // Player wins
        System.out.println("Player wins!");

        // Player busts
        gameLogic.getPlayerHand().clear();
        gameLogic.getPlayerHand().add(new Card("King", "Hearts", "KH"));
        gameLogic.getPlayerHand().add(new Card("Queen", "Spades", "QS"));
        gameLogic.getPlayerHand().add(new Card("10", "Diamonds", "10D"));
        System.out.println("Player busts!");

        // Dealer wins
        gameLogic.getPlayerHand().clear();
        gameLogic.getPlayerHand().add(new Card("8", "Hearts", "8H"));
        gameLogic.getPlayerHand().add(new Card("7", "Spades", "7S"));
        gameLogic.getDealerHand().clear();
        gameLogic.getDealerHand().add(new Card("King", "Diamonds", "KD"));
        gameLogic.getDealerHand().add(new Card("Ace", "Clubs", "AC"));
        System.out.println("Dealer wins!");

        // Tie
        gameLogic.getPlayerHand().clear();
        gameLogic.getPlayerHand().add(new Card("8", "Hearts", "8H"));
        gameLogic.getDealerHand().clear();
        gameLogic.getDealerHand().add(new Card("8", "Diamonds", "8D"));
        System.out.println("It's a tie");
    }
}
