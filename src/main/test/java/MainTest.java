import com.blackjackgame.Main;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void testMainGameFlow() {
        // Simulate user input
        systemInMock.provideText("H\nS\n"); // Simulate hitting and then standing

        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Call the main method
        Main.main(null);

        // Restore the original standard output
        System.setOut(originalOut);

        // Check if the game output contains expected strings
        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("Player wins"));
    }
}