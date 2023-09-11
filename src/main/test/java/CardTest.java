import com.blackjackgame.cards.Card;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    private Card card;

    @Before
    public void setUp() {
        // Create a Card object before each test
        card = new Card("Ace", "Spades", "AS");
    }

    @Test
    public void testGetValue() {
        assertEquals("Ace", card.getValue());
    }

    @Test
    public void testGetSuit() {
        assertEquals("Spades", card.getSuit());
    }

    @Test
    public void testGetCode() {
        assertEquals("AS", card.getCode());
    }

    @Test
    public void testToString() {
        assertEquals("Ace of Spades", card.toString());
    }

    @Test
    public void testSetFaceUp() {
        card.setFaceUp(true);
        assertTrue(card.isFaceUp());
    }

    @Test
    public void testIsFaceCard() {
        assertFalse(card.isFaceCard());

        // Create a non-face card and test again
        Card nonFaceCard = new Card("2", "Diamonds", "2D");
        assertFalse(nonFaceCard.isFaceCard());
    }

    @Test
    public void testGetRank() {
        assertEquals(1, card.getRank());

        // Test for numeric value
        Card numericCard = new Card("7", "Hearts", "7H");
        assertEquals(7, numericCard.getRank());

        // Test for 10-value cards
        Card tenValueCard = new Card("10", "Clubs", "10C");
        assertEquals(10, tenValueCard.getRank());

        // Test for invalid value (null)
        Card invalidCard = new Card(null, "InvalidSuit", "XX");
        assertEquals(0, invalidCard.getRank());
    }
}
